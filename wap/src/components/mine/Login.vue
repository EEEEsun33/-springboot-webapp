<template>
<div>
  <div class="mui-content">
    <form id='login-form' class="mui-input-group">
      <!-- <div class="mui-input-row">
        <label>用户名</label>
        <input v-model="customer_username" type="text" class="mui-input-clear mui-input" placeholder="请输入用户名">
      </div> -->
      <div class="mui-input-row">
        <label>手机号</label>
        <input id='account' v-model="customer_num" type="text" class="mui-input-clear mui-input" maxlength="11" placeholder="请输入手机号">
      </div>
      <div class="mui-input-row">
        <label>密码</label>
        <input id='password' v-model="customer_password" type="password" class="mui-input-clear mui-input" placeholder="请输入密码">
      </div>
    </form>
    <form class="mui-input-group">
      <ul class="mui-table-view mui-table-view-chevron">
        <li class="mui-table-view-cell">
          自动登录
          <div id="autoLogin" class="mui-switch">
            <div class="mui-switch-handle"></div>
          </div>
        </li>
      </ul>
    </form>
    <div class="mui-content-padded">
      <button  class="mui-btn mui-btn-block mui-btn-primary" @click="login">登录</button>
      <div class="link-area"><a id='reg' @click="register">注册账号</a> <span class="spliter">|</span> <a id='forgetPassword'>忘记密码</a>
      </div>
    </div>
    <div class="mui-content-padded oauth-area">

    </div>
  </div>

</div>
</template>

<script>
  import {Toast} from 'mint-ui';
  import { mapMutations } from 'vuex';

    export default {
      name: "Login",
      data () {
        return {
          schoolList: null,
          schoolId: 1,
          customer_num: null,
          customer_password: null,
          customer_username: null
        }
      },
      mounted:function(){

      },
      created(){

      },
      methods:{
        login(){
          console.log(this.customer_num);
          console.log(this.customer_password);
          if(!(/^1[3456789]\d{9}$/.test(this.customer_num))){
            Toast("手机号格式错误!")
          }
          if(!this.customer_num){
            Toast("请输入手机号!")
            return
          }
          if(!this.customer_password){
            Toast("请输入密码!")
            return
          }

          let param = new URLSearchParams()
          param.append('phone', this.customer_num)
          param.append('password',this.customer_password)

          this.axios.post("/buyer/login",param).then(response =>{
            console.log(response.data.data);
            console.log(response.data.data[0].buyerInfo);
            console.log(response.data.data[0].token);
            //将用户token保存到vuex中
            this.$store.commit("$_setStorage", response.data.data[0].token);
            this.$store.commit("$_setAccount", response.data.data[0].buyerInfo.username);
            this.$store.commit("$_setUserPhone", response.data.data[0].buyerInfo.phone);
            this.$store.commit("$_setBuyerId", response.data.data[0].buyerInfo.buyerId);
             if(response.data.code == 0){
               console.log("登录成功")             
               this.$router.push('/mine')
             }else Toast("账号或者密码有误！");

          })

        },
        register(){
          this.$router.push('/register')
        }
      }
    }
</script>

<style scoped>
  .area {
    margin: 20px auto 0px auto;
  }

  .mui-input-group {
    margin-top: 10px;
  }

  .mui-input-group:first-child {
    margin-top: 20px;
  }

  .mui-input-group label {
    width: 22%;
  }

  .mui-input-row label~input,
  .mui-input-row label~select,
  .mui-input-row label~textarea {
    width: 78%;
  }

  .mui-checkbox input[type=checkbox],
  .mui-radio input[type=radio] {
    top: 6px;
  }

  .mui-content-padded {
    margin-top: 25px;
  }

  .mui-btn {
    padding: 10px;
  }

  .link-area {
    display: block;
    margin-top: 25px;
    text-align: center;
  }

  .spliter {
    color: #bbb;
    padding: 0px 8px;
  }

  .oauth-area {
    position: absolute;
    bottom: 20px;
    left: 0px;
    text-align: center;
    width: 100%;
    padding: 0px;
    margin: 0px;
  }

  .oauth-area .oauth-btn {
    display: inline-block;
    width: 50px;
    height: 50px;
    background-size: 30px 30px;
    background-position: center center;
    background-repeat: no-repeat;
    margin: 0px 20px;
    /*-webkit-filter: grayscale(100%); */
    border: solid 1px #ddd;
    border-radius: 25px;
  }

  .oauth-area .oauth-btn:active {
    border: solid 1px #aaa;
  }

  .oauth-area .oauth-btn.disabled {
    background-color: #ddd;
  }

  .mui-content{
    background-color: #efeff4;
  }
</style>
