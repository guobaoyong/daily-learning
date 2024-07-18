// pages/blog/blog.js
let blogListLength = 0
let keyword = '' // 搜索关键字
const app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    //控制底部弹出层是否显示
    modalShow: false,
    blogList: [],
    isBlog: true, // 是否有博客数据
    isMore: '正在加载...', // 是否还有更多数据
  },

  /**
   * 发布功能
   */
  onPublish() {
    // 此判断对已存在用户数据时可减少卡顿
    if (Object.keys(app.getGlobalData('userInfo')).length != 0) { // 是否已经授权过并且获取了昵称头像
      this.onLoginSuccess({
        detail: app.getGlobalData('userInfo')
      })
    } else {
      // 判断用户是否授权
      wx.getSetting({
        success: (res) => { // 这里使用箭头函数可改变内部this指向为外部的this
          if (res.authSetting['scope.userInfo']) { // 已授权
            wx.getUserInfo({ // 获取用户信息
              success: (res) => { // 这里使用箭头函数可改变内部this指向为外部的this

                app.setGlobalData('userInfo', res.userInfo) // 设置app全局属性

                this.onLoginSuccess({
                  detail: res.userInfo
                })
              }
            })
          } else { // 未授权
            this.setData({ // 打开弹出层，显示获取用户信息按钮
              modalShow: true
            })
          }
        }
      })
    }
  },

  onLoginSuccess(event){
    const detail = event.detail
    wx.navigateTo({
      url: `../blog-edit/blog-edit?nickName=${detail.nickName}&avatarUrl=${detail.avatarUrl}`,
    })
  },

  onLoginFail(){
    wx.showModal({
      title: '授权用户才能发布',
      content: '',
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    this._loadBlogList()
  },

  // 搜索
  onSearch(event) {
    keyword = event.detail.keyword
    this.setData({
      blogList: []
    })
    this._loadBlogList(0)
    this._getBlogListLength() // 获取博客总计数
  },

  //通过云函数获取博客列表的数据，并保存下来
  // 加载博客列表数据
  _loadBlogList(start=0, mode) {

    if (mode === 'refresh') {
      this._getBlogListLength() // 获取博客总计数
      wx.showLoading({
        title: '正在刷新',
      })
      this.setData({
        isMore: '正在加载...'
      })
    }

    wx.cloud.callFunction({
      name: 'blog',
      data: {
        keyword,
        start,
        count: 10, // 每次加载几条
        $url: 'list'
      }
    }).then((res) => {
      if (mode === 'refresh') {
        this.setData({
          blogList: []
        })
        wx.hideLoading()
      }
      this.setData({
        blogList: this.data.blogList.concat(res.result)
      })

      if (this.data.blogList.length === 0) {
        this.setData({
          isBlog: false,
          isMore: ''
        })
      } else {
        this.setData({
          isBlog: true
        })
      }


      wx.stopPullDownRefresh()
    })
  },

  // 获取博客总计数
  _getBlogListLength() {
    wx.cloud.callFunction({
      name: 'blog',
      data: {
        $url: 'getBlogListLength'
      }
    }).then((res) => {
      blogListLength = res.result.total
    })
  },

  // 进入博客卡片详情
  goComment(event) {
    wx.navigateTo({
      url: '../../pages/blog-comment/blog-comment?blogId=' + event.target.dataset.blogid,
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function() {
    this.setData({
      blogList: []
    })
    this._loadBlogList(0,'refresh')
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {
    let bl = this.data.blogList.length
    if (bl != blogListLength) {
      this._loadBlogList(bl)
    } else {
      this.setData({
        isMore: '没有更多了'
      })
    }
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function (event) {
    // 对分享卡片的设置
    let blogObj = event.target.dataset.blog
    return {
      title: blogObj.content,
      path: `/pages/blog-comment/blog-comment?blogId=${blogObj._id}`,
      //path: `/pages/blog/blog?blogId=${blogObj._id}&share=1`,
      // imageUrl: '' // 自定义图片，不支持云存储的图片
    }
  }
})