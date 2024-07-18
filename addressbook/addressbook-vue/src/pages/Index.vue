<template>
  <div>
    <phone-book-header></phone-book-header>
    <phone-book-search
      :phoneBooks="phoneBooks"></phone-book-search>
    <phone-book-list
      :letter="letter"
      :phoneBooks="phoneBooks"></phone-book-list>
    <phone-book-alphabet
      @change="handleLetterChange"
      :phoneBooks="phoneBooks"></phone-book-alphabet>
  </div>
</template>

<script>
    import axios from 'axios'
    import PubSub from 'pubsub-js'
    import {getServerUrl} from '@/config/sys.js'
    import PhoneBookHeader from './components/Header'
    import PhoneBookSearch from './components/Search'
    import PhoneBookList from './components/List'
    import PhoneBookAlphabet from './components/Alphabet'

    export default {
        name: "Index",
        data(){
            return{
                phoneBooks:{},
                letter:''
            }
        },
        components:{
            PhoneBookHeader,
            PhoneBookSearch,
            PhoneBookList,
            PhoneBookAlphabet
        },
        methods:{
            refreshToken(){
                let token=window.localStorage.getItem("token")
                axios.defaults.headers.common['token']=token
                let url=getServerUrl("refreshToken");
                axios.get(url)
                    .then(response=>{
                        console.log("token刷新："+response.data.token)
                        window.localStorage.setItem("token",response.data.token);
                    }).catch(error=>{
                    console.log(error)
                })
            },
            getAddressbooks(){
                let token=window.localStorage.getItem("token")
                axios.defaults.headers.common['token']=token
                let url=getServerUrl("addressbook/loadAll");
                axios.get(url)
                    .then(response=>{
                        console.log(response);
                        this.phoneBooks=response.data.data;
                        if (response.data.code != '0'){
                            this.$router.replace('/');
                        }
                    }).catch(error=>{
                    console.log(error);
                    this.$router.replace('/');
                })
            },
            handleLetterChange(letter){
                console.log('传来的字母:'+letter)
                this.letter=letter
            }
        },
        mounted() {
            setInterval(this.refreshToken,1000*60*10); // 10分钟刷新一次token
            this.getAddressbooks(); // 加载所有通讯录信息
            PubSub.subscribe('refreshPhoneBook',(msg,data)=>{
                console.log('收到消息 refreshPhoneBook')
                this.getAddressbooks()
            })
        }
    }
</script>

<style scoped>

</style>

