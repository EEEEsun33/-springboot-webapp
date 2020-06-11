// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router/index.js'
//d导入并安装Vue-resource
import VueResource from 'vue-resource'
Vue.use(VueResource)

//导入bootstrap样式
import 'bootstrap/dist/css/bootstrap.min.css'
//导入mui样式
import './assets/mui/css/mui.css'
import './assets/mui/css/style.css'
import './assets/mui/css/icons-extra.css'
import './assets/mui/css/app.css'
//导入Mint UI组件
import Mint from 'mint-ui';
import 'mint-ui/lib/style.css';
import store from './store/index.js'
Vue.use(Mint);   //把Mint ui注册为全局组件
Vue.config.productionTip = false



//配置请求后端的数据接口
import axios from 'axios'
Vue.prototype.axios=axios
axios.defaults.timeout = 10000
axios.defaults.baseURL = 'http://localhost:8080/sell'
axios.defaults.headers.post['Contenst-Type'] = 'application/json;'
axios.defaults.headers.common['token'] = store.state.token  // 在header中添加token

// 请求时的拦截
// axios.interceptors.request.use(function (config) {
//   // 发送请求之前做一些处理
//   //在登录的时候，后台会将token和loginId传递过来，拿到值以后，用localStorage.setItem("jwt",JSON.stringify(data));存起来
//   // let storageData = localStorage.getItem("jwt");
//   let storageData = localStorage.getItem("token");
//   if(storageData){
//     storageData = JSON.parse(storageData);
//     let  token = storageData.token,
//     loginId = storageData.loginId;
//     config.headers['X-Token'] = token;
//     config.headers['X-LoginId'] = loginId;
//   }
//   return config;
// }, function (error) {
//   // 当请求异常时做一些处理
//   return Promise.reject(error);
// });


// 响应时拦截
axios.interceptors.response.use(function (response) {
  // 返回响应时做一些处理
  console.log("返回的数据",response);
  return response;
}, function (error) {
  // 当响应异常时做一些处理,当token过期时，跳转到登录页面
  return Promise.reject(error);
});

// 请求时的拦截
// 添加全局header签名
axios.interceptors.request.use(
  config => {
    if (store.state.token) {
      config.headers.common['token'] = store.state.token
    }
    return config;
  },
  error => {
    router.push('/login')
    //请求错误
    return Promise.reject(error);
  }
 
)

// axios.interceptors.request.use(config => {
//   // 如果不是FormData类型，且为post请求，则进行数据的序列化
//   if (Object.prototype.toString.call(config.data) !== '[object FormData]' && config.method === 'post') {
//     const qs = require('qs');
//   // 请求拦截器处理
//   config.data = qs.stringify(config.data)
//   }
//   return config
//   }, (err) =>{
//   return Promise.reject(err);
//   }
// )
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
