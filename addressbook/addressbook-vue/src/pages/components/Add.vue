<template>
  <div class="detail">
    <div class="header">
      <router-link tag="div" to="/addressbook" class="iconfont">&#xe667;</router-link>
    </div>

    <div class="image">
      <div class="info">
        <img :src="getImageUrl(phoneBook.image)" @click="showGalleryClick"/>
      </div>
      <div class="action">
        <button @click="imageClick">修改头像</button>
        <input type="file" name="file" accept="image/png,image/gif,image/jpeg,image/jpg" ref="file" @change="upload">
      </div>
    </div>

    <div class="userInfo">
      <div class="item border-bottom">
        <span>姓名</span>
        <input type="text" v-model="phoneBook.name">
      </div>
      <div class="item border-bottom">
        <span>手机号码</span>
        <input type="text" v-model="phoneBook.phoneNumber">
      </div>
      <div class="item border-bottom">
        <span>座机电话号码</span>
        <input type="text" v-model="phoneBook.teleNumber">
      </div>
      <div class="item border-bottom">
        <span>工作单位地址</span>
        <textarea v-model="phoneBook.workAddress"></textarea>
      </div>
      <div class="item border-bottom">
        <span>家庭地址</span>
        <textarea v-model="phoneBook.homeAddress"></textarea>
      </div>
      <div class="item border-bottom">
        <span>备注</span>
        <textarea v-model="phoneBook.remark"></textarea>
      </div>
    </div>

    <div class="action">
      <div class="item border-bottom">
        <a @click="addInfo">保存</a>
      </div>
    </div>

    <gallery
      v-show="showGallery"
      :galleryImage="galleryImage"
      @hideGalleryClick="hideGalleryClick"></gallery>

  </div>
</template>

<script>
    import axios from 'axios'
    import PubSub from 'pubsub-js'
    import {getServerUrl} from '@/config/sys.js'
    import Gallery from '@/pages/common/Gallery'

    export default {
        name: "Add",
        data(){
            return{
                phoneBook:{image:'default.jpg'},
                showGallery:false,
                galleryImage:''
            }
        },
        components:{
            Gallery
        },
        methods:{
            getImageUrl(image){
                return getServerUrl('image/'+image);
            },
            imageClick(){
                this.$refs.file.click();
            },
            showGalleryClick(){
                this.galleryImage=getServerUrl('image/'+this.phoneBook.image);
                this.showGallery=true
            },
            hideGalleryClick(){
                this.showGallery=false
            },
            addInfo(){
                let url=getServerUrl('addressbook/save');
                let token=window.localStorage.getItem('token')
                axios.defaults.headers.common['token'] = token;
                if(this.phoneBook.name==null || this.phoneBook.name.trim()==''){
                    alert("姓名不为空！")
                    return;
                }
                if(this.phoneBook.phoneNumber==null || this.phoneBook.phoneNumber.trim()==''){
                    alert("手机号码不为空！")
                    return;
                }
                if(!(/^1[3456789]\d{9}$/.test(this.phoneBook.phoneNumber))){
                    alert("手机号码有误，请重填");
                    return;
                }
                if(this.phoneBook.teleNumber!=null && this.phoneBook.teleNumber.trim()!='' && !/^(\(\d{3,4}\)|\d{3,4}-|\s)?\d{7,14}$/.test(this.phoneBook.teleNumber)){
                    alert('固定电话有误，请重填');
                    return false;
                }
                axios.post(url,{"name":this.phoneBook.name,"phoneNumber":this.phoneBook.phoneNumber,"teleNumber":this.phoneBook.teleNumber,"workAddress":this.phoneBook.workAddress,"homeAddress":this.phoneBook.homeAddress,"image":this.phoneBook.image,"remark":this.phoneBook.remark,"image":this.phoneBook.image})
                    .then(response=>{
                        if(response.data.code==0){
                            alert("添加成功")
                            PubSub.publish('refreshPhoneBook','')
                            this.$router.replace('/addressbook')
                        }else{
                            alert(response.data.msg)
                        }
                    }).catch(error=>{
                    alert(error)
                })
            },
            upload(e){
                let file=e.target.files[0];
                let param=new FormData();
                param.append('file',file,file.name);
                console.log(param.get('file'));

                let token=window.localStorage.getItem("token")
                let url=getServerUrl("uploadImage");
                let config={
                    headers:{'Content-Type':'multipart/form-data','token':token}
                };
                axios.post(url,param,config)
                    .then(response=>{
                        if(response.data.code==0){
                            this.phoneBook.image=response.data.data.title;
                        }
                    }).catch(error=>{
                    console.log(error)
                })
            }
        },
    }
</script>

<style lang="stylus" scoped>

  @import "~@/assets/styles/varibles.styl"

  .border-bottom
    &:before
      border-color: $borderColor

  .detail
    .header
      position: fixed
      top: 0
      overflow: hidden
      height: $headerHeight
      line-height: $headerHeight
      text-align: left
      padding-left: .2rem
      padding-top: .1rem
      background-color: $borderColor
      width: 100%
      z-index: 10
      opacity: .8
    .image
      margin-top: .86rem
      text-align: center
      width: 90%
      height: 2.5rem
      .info
        img
          float: left
          border-radius: .5rem
          overflow: hidden
          padding: .3rem
          width: 2rem
          height: 2rem
      .action
        padding-top: 1.55rem
        text-align: left
        button
          background-color: #4caf50
          border: none
          color: #fff
          padding: .15rem
          text-align: center
          text-decoration: none
          display: inline-block
          font-size: 16px
          border-radius: .2rem
        input
          display: none
    .userInfo
      padding-left: .3rem
      padding-top: 0
      .item
        padding-top: .3rem
        padding-bottom: .1rem
        span
          display: block
          font-weight: 700
        input
          padding-top: .1rem
          width: 100%
        textarea
          padding-top: .1rem
          width: 100%
    .action
      padding-top: .5rem;
      padding-bottom: 2rem;
      .item
        width: 100%;
        text-align: center;
        padding-top: .3rem;
        padding-bottom: .3rem;
        a
          font-size: .4rem;
          color: #576b97;

</style>
