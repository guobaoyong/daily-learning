// pages/blog-edit/blog-edit.js
const MAX_WORDS_NUM = 140
const MAX_IMG_NUM = 9
const db = wx.cloud.database() //初始化数据库
let content = ''  //当前输入的文字内容
let userInfo = {} //存储用户信息

Page({

  /**
   * 页面的初始数据
   */
  data: {
    //输入的文字个数
    wordsNum: 0,
    footerBottom: 0,
    images: [],
    selectPhoto: true
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    userInfo = options
  },

  onInput(event){
    let wordsNum = event.detail.value.length
    if(wordsNum >= MAX_WORDS_NUM){
      wordsNum = `最大字数为${MAX_WORDS_NUM}`
    }
    this.setData({
      wordsNum
    })
    content = event.detail.value
  },

  onFocus(event){
    this.setData({
      footerBottom: event.detail.height,
    })
  },

  onBlur(){
    this.setData({
      footerBottom: 0,
    })
  },

  onChooseImage(){
    let max = MAX_IMG_NUM - this.data.images.length
    wx.chooseImage({
      count: max,
      sizeType: ['original','compressed'],
      sourceType: ['album','camera'],
      success: (res) =>{
        this.setData({
          images: this.data.images.concat(res.tempFilePaths), 
        })
        max = MAX_IMG_NUM - this.data.images.length
        this.setData({
          selectPhoto: max <= 0 ? false : true,
        })
      },
    })
  },

  onDelImage(event){
    this.data.images.splice(event.target.dataset.index, 1)
    this.setData({
      images: this.data.images,
    })
    if (this.data.images.length == MAX_IMG_NUM - 1){
      this.setData({
        selectPhoto: true,
      })
    }
  },

  onPreviewImage(event){
    wx.previewImage({
      urls: this.data.images,
      current: event.target.dataset.imgsrc,
    })
  },

  // 点击发布，将文件上传云存储
  send() {
    // 2.数据上传->云数据库
    // 数据库需要存储 ：内容，图片fileID ，openid , 昵称，头像，发布时间
    // 1.图片并不是直接上传到数据库： 而是->云存储，云存储返回fileID ，云文件ID，
    if (content.trim() === '') {
      wx.showModal({
        title: '请输入内容',
        content: '',
      })
      return
    }
    wx.showLoading({
      title: '发布中',
      mask: true,
    })
    let promiseArr = []
    let fileIds = []
    //图片上传,一次只能上传一张，所以需要循环遍历
    for (let i = 0, len = this.data.images.length; i < len; i++) {
      let p = new Promise((resolve, reject) => {
        let item = this.data.images[i]
        //获取文件扩展名
        let suffix = /\.\w+$/.exec(item)[0]
        wx.cloud.uploadFile({
          //保证上传路径和文件名唯一
          cloudPath: 'blog/' + Date.now() + '-' + Math.random() * 1000000 + suffix,
          filePath: item,
          success: (res) => {
            fileIds = fileIds.concat(res.fileID)
            resolve()
          },
          fail: (err) => {
            console.error()
            reject()
          }
        })
      })
      promiseArr.push(p)
    }
    //循环结束，promiseArr中已经有多个promise对象
    // all执行完所有
    Promise.all(promiseArr).then(res => {
      db.collection('blog').add({
        data: {
          ...userInfo,
          content,
          img: fileIds,
          createTime: db.serverDate() //上传数据到服务端的事件
        }
      }).then(res => {
        wx.hideLoading()
        wx.showToast({
          title: '发布成功',
        })
        // 返回bolg页面，刷新整个列表，并将最新的数据展示出来
        wx.navigateBack()
        // 取到当前小程序的blog界面，调用下拉刷新数据的那个函数
        // 做到点击完发布后，刷新界面数据展示出来
        // 能够获取到初始页面 ->当前页面 的所有页面
        const pages = getCurrentPages()
        // 取到上一个页面（blog）
        const prevPage = pages[pages.length - 2]
        //调用上一个页面的下拉刷新方法
        prevPage.onPullDownRefresh()
      })
    }).catch(err => {
      wx.hideLoading()
      wx.showToast({
        title: '发布失败',
      })
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})