<template>
    <div class="container" @click="hideClick">
        <div class="wrapper">
          <img :src="galleryImage" alt="">
        </div>
    </div>
</template>

<script>
    export default {
        name: "Gallery",
        props:{
          galleryImage:String
        },
        methods:{
          hideClick(){
            this.$emit('hideGalleryClick')
          }
        }
    }
</script>

<style lang="stylus" scoped>

  .container
    display: flex
    flex-direction: column
    justify-content: center
    z-index: 99
    position: fixed
    left: 0
    right: 0
    top: 0
    bottom: 0
    background: #000
    .wrapper
      width: 100%
      height: 0
      padding-bottom: 100%
      img
        width:100%
</style>
