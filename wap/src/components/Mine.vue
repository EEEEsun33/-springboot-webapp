<template>
  <div class="mui-page-content">
      <div>
        <ul class="mui-table-view mui-table-view-chevron">
          <li class="mui-table-view-cell mui-media">
            <!-- <router-link class="mui-navigate-right" to="/info"> -->
              <img class="mui-media-object mui-pull-left head-img" id="head-img" src="../assets/images/head.jpg">
              <div class="mui-media-body">
                <p class="mui-ellipsis">{{username}}</p>
                <p class="mui-ellipsis">账号: {{ phone }}</p>
              </div>
            </router-link>
          </li>
        </ul>
        <br/>
        <ul class="mui-table-view mui-table-view-chevron">
          <li class="mui-table-view-cell">
            <router-link to="wallet" class="mui-navigate-right" >我的钱包</router-link>
          </li>
        </ul>

        <br/>
        <ul class="mui-table-view mui-table-view-chevron">
          <li class="mui-table-view-cell">
            <a  class="mui-navigate-right">新消息通知</a>
          </li>
          <li class="mui-table-view-cell">
            <router-link  class="mui-navigate-right" to="comments">我的评价</router-link>
          </li>
          <li class="mui-table-view-cell">
            <a  class="mui-navigate-right">通用</a>
          </li>
          <li class="mui-table-view-cell">
            <router-link  class="mui-navigate-right" to="advice">建议反馈</router-link>
          </li>
        </ul>
        <ul class="mui-table-view mui-table-view-chevron">
          <li class="mui-table-view-cell">
            <router-link  class="mui-navigate-right" to="about">关于点餐平台 <i class="mui-pull-right update">V1.0</i></router-link>
          </li>
        </ul>
        <br/><br/><br/>
        <button class="mui-btn mui-btn-danger mui-btn-block quit" @click="logout">退出登录</button>
      </div>
  </div>

</template>

<script>
import store from "@/store/index";
import {Toast} from 'mint-ui'
    export default {
      inject: ['reload'],
      name: "Mine",
      store,
      data() {
        return {
          username: '',
          phone: ''
        }
        
      },
      created(){
        var token = localStorage.getItem('token');
        if(!token){
          Toast("请先登录!")
          this.$router.push('/login')
        }
        // this.username = this.$store.state.account;
        // this.phone = this.$store.state.userPhone;
        this.username = localStorage.getItem('account');
        this.phone = localStorage.getItem('userPhone');
        console.log(this.username+this.phone);
      },
      methods:{
          logout(){
            localStorage.removeItem("token")
            localStorage.removeItem("account")
            localStorage.removeItem("userPhone")
            localStorage.removeItem("buyerId")
            Toast("退出成功！")
            // location.reload()
            this.reload();
            this.$router.push('/login')
          },
      }
    }
</script>

<style scoped>
.mui-page-content{
  background-color: #efeff4;
}
  .quit{
    margin: 0 auto;
    width: 95%;

  }

a:after,a:link,a:active,a:visited{
  text-decoration:none;
}
</style>
