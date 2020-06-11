import Vue from "vue"
import Vuex from "vuex"

Vue.use(Vuex);
const key ='token'
const account_key = 'account'
const userPhone = 'userPhone'
const buyerId = 'buyerId'

const store = new Vuex.Store({
    // 状态，一些常量
    // 从 store 中读取状态时，若 store 中的状态发生变化，那么相应的组件也会更新
    state() {
        return{
            token:localStorage.getItem('token') ? localStorage.getItem('token'):'',
            account:localStorage.getItem('account') ? localStorage.getItem('account'):'',
            userPhone:localStorage.getItem('userPhone') ? localStorage.getItem('userPhone'):'',
            buyerId:localStorage.getItem('buyerId') ? localStorage.getItem('buyerId'):''          
          }
    },
    // mutations处理一些突变
    // 改变store中状态的唯一途径是显示地提交mutation。
    mutations: {
        $_setStorage(state,value){
            state.token =value;
            localStorage.setItem(key,value)
        },
        $_setAccount(state,account_va){
            // 将用户名存入state
            state.account =account_va
            // 将用户名存入loaclStorage
            localStorage.setItem("account",account_va)
        },
        $_setUserPhone(state,objPhone){
            state.userPhone = objPhone;
            localStorage.setItem("userPhone",objPhone)
        },
        $_setBuyerId(state,buyerId){
            state.buyerId = buyerId;
            localStorage.setItem("buyerId",buyerId)
        }
    },
    getters: {
        getSortage:function (state) {
            if(!state.token){
              state.token =JSON.parse(localStorage.getItem(key))
            }
            return state.token
        },
        getaccount: function(state){
            state.account=JSON.parse(localStorage.getItem(account_key))
            return state.account
        },
        getUserPhone: function(state) {
            state.userPhone = JSON.parse(localStorage.getItem(userPhone))
            return state.userPhone
        },
        getBuyerId: function(state) {
            state.buyerId = JSON.parse(localStorage.getItem(buyerId))
            return state.buyerId
        }
    }
})

//导出store
export default store;