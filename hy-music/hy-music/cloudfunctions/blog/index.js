// 云函数入口文件
const cloud = require('wx-server-sdk')

cloud.init()

const TcbRouter = require('tcb-router') // 导入tcb-router
const db = cloud.database() // 初始云数据库
const blogCollection = db.collection('blog') //获取blog集合数据
// 每次查询的数据有限制，假设每次最多查询100条
const MAX_LIMIT = 100

// 云函数入口函数
exports.main = async (event, context) => {
  const app = new TcbRouter({
    event
  })

  app.router('list', async (ctx, next) => {
    const keyword = event.keyword
    let w = {} //还要再下列blogCollection后添加.where(w) ,如果没有输入就没有查询条件
    if (keyword.trim() != '') {
      w = {
        content: db.RegExp({
          regexp: keyword,
          options: 'i', //i 忽略大小写
        })
      }
    }
    // orderBy('createTime','desc') 按照createTime时间逆序排列
    let data = await blogCollection.where(w).skip(event.start).limit(event.count)
      .orderBy('createTime', 'desc').get().then(res => {
        return res.data
      })

    // 获取评论条数
    let commentCount = {}
    for (let i = 0; i < data.length; i++) {
      commentCount = await db.collection('blog-comment').where({
        blogId: data[i]._id
      }).count()
      // 不能直接在.count()后面添加 total，因为是异步函数，没获取到数据直接添加total取到的是undefined
      data[i].commentLength = commentCount.total
    }

    ctx.body = data
  })

  // 获取博客列表总长度
  app.router('getBlogListLength', async (ctx, next) => {
    ctx.body = await blogCollection.count()
  })

  // 博客详情(博客内容、评论)
  app.router('detail', async (ctx, next) => {
    let blogId = event.blogId

    // 博客内容
    let detail = await blogCollection.where({
      _id: blogId
    }).get().then((res) => {
      return res.data
    })

    // 评论查询
    const countResult = await db.collection('blog-comment').count()
    const total = countResult.total
    let commentList = {
      data: []
    }
    if (total > 0) {
      // 突破100条限制
      const batchTimes = Math.ceil(total / MAX_LIMIT)
      const tasks = []
      for (let i = 0; i < batchTimes; i++) {
        let promise = db.collection('blog-comment').skip(i * MAX_LIMIT)
          .limit(MAX_LIMIT).where({
            blogId
          }).orderBy('createTime', 'desc').get()
        tasks.push(promise)
      }
      if (tasks.length > 0) {
        commentList = (await Promise.all(tasks)).reduce((acc, cur) => {
          return {
            data: acc.data.concat(cur.data)
          }
        })
      }

    }
    ctx.body = {
      detail,
      commentList
    }
  })

  const wxContext = cloud.getWXContext()
  // 获取我的博客列表总长度
  app.router('getMyBlogListLength', async (ctx, next) => {
    ctx.body = await blogCollection.where({
      _openid: wxContext.OPENID
    }).count()
  })

  // 我的博客
  app.router('getListByOpenid', async (ctx, next) => {
    let data = await blogCollection.where({
      _openid: wxContext.OPENID
    }).skip(event.start).limit(event.count)
      .orderBy('createTime', 'desc').get().then((res) => {
        return res.data
      })

    // 获取评论条数
    let commentCount = {}
    for (let i = 0; i < data.length; i++) {
      commentCount = await db.collection('blog-comment').where({
        blogId: data[i]._id
      }).count()
      // 不能直接在.count()后面添加 total，因为是异步函数，没获取到数据直接添加total取到的是undefined
      data[i].commentLength = commentCount.total
    }

    ctx.body = data
  })

  return app.serve()
}