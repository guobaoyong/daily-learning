// components/blog-card/blog-card.js
import formatTime from '../../utils/formatTime.js'

Component({
  /**
   * 组件的属性列表
   */
  properties: {
    blog: Object
  },

  observers: {
    ['blog.createTime'](val) {
      if (val) {
        this.setData({
          _createTime: formatTime(new Date(val))
        })
      }

    }
  },

  /**
   * 组件的初始数据
   */
  data: {
    _createTime: ''
  },

  /**
   * 组件的方法列表
   */
  methods: {
    // 通过给标签绑定自定义属性，在取到对应的值展示
    onPreviewImage(event) {
      // 获取自定义属性的值
      const ds = event.target.dataset
      wx.previewImage({
        // 当前需要预览的所有图片
        urls: ds.imgs,
        // 当前正在预览的图片的地址
        current: ds.imgsrc,
      })
    },
  }
})
