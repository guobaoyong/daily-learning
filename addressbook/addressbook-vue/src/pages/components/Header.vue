<template>
  <div class="header">
    <a href="javascript:;" class="iconfont exit-icon" @click="exit()">&#xe792;</a>
    通讯录系统
    <router-link tag="div" to="/add" class="iconfont add-icon">&#xe81b;</router-link>
  </div>
</template>

<script>
    export default {
        name: "Header",
        methods:{
            exit() {
                window.localStorage.removeItem("token");
                this.$router.replace('/')
            }
        }
    }
</script>

<style lang="stylus" scoped>

  @import "~@/assets/styles/varibles.styl"

  .header
    position: relative
    overflow: hidden
    background: $bgColor
    font-size: .35rem
    font-weight: 700
    text-align: center
    height: .86rem
    line-height: .86rem
    .add-icon
      position: fixed
      right: .45rem
      top: 0rem
    .exit-icon
      position: fixed
      left: .45rem
      top: 0rem
</style>
