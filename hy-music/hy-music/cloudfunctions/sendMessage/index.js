// 云函数入口文件
const cloud = require('wx-server-sdk')

cloud.init()

// 云函数入口函数
exports.main = async (event, context) => {
  // 获取openid
  const { OPENID } = cloud.getWXContext()

  // 模板推送消息
  const result = await cloud.openapi.templateMessage.send({
    touser: OPENID,
    page: `/pages/blog-comment/blog-comment?blogId=${event.blogId}`, // 用户点击推送消息打开的页面
    data: { // 模板的内容，keyword为在公众平台设置模板时对应的字段
      keyword1: { // 评价状态
        value: '评价完成'
      },
      keyword2: { // 评价内容
        value: event.content
      }
    },
    templateId: 'IG2QHRWAHfq1R6aZuv2VJprw8R2ivVhmMC4xsla-kN4', // 模板id，到公众平台模板消息上获取
    formId: event.formId // 触发消息推送的form表单的id
  })

  return result
}