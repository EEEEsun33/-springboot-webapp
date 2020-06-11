<template>
  <div>
    <mt-navbar v-model="selected">
        <mt-tab-item id="1">待送达</mt-tab-item>
        <mt-tab-item id="2">已取消</mt-tab-item>
        <mt-tab-item id="3">待评价</mt-tab-item>
        <mt-tab-item id="4">已完成</mt-tab-item>
    </mt-navbar>

    <!-- tab-container -->
    <mt-tab-container v-model="selected">
        <mt-tab-container-item id="1">

            <ul class="mui-table-view mui-table-view-chevron" v-if="wait && wait.length>0">
                <li class="mui-table-view-cell mui-media" v-for="(item,index) in wait" :key="index">
                    <router-link class="mui-navigate-right" :to="'/orderDetail/'+item.orderId">
                        <div>
                            <span>订单号：{{item.orderId}}</span>
                            <span class="orderStatus">{{item.orderStatusStr}}</span>
                        </div>
                        <div class="mui-row">
                            <div class="mui-col-xs-3" style="padding: 0 2% 0 0">
                                <!-- <img  class="head-img" :src="website+item.goods.productPath"> -->
                                <img class="head-img" :src="require('../assets/images/'+item.orderDetailList[0].productIcon)" alt="">
                            </div>
                            <div class="mui-media-body mui-col-xs-9" style="margin: 0;padding: 0">                               
                                <!-- {{ item.orderDetailList[0].productName}} -->
                                <p class="nameList">
                                    <span v-for="(sonItem,i) in item.orderDetailList" :key="i">
                                    {{sonItem.productName}} +
                                    </span>
                                </p>  
                                <span style="float: right">￥ <span class="price">{{item.orderAmount}}</span></span>
                                <p class='mui-ellipsis'>
                                共 {{item.orderDetailList.length}} 件
                                </p>
                                <p class="orderTime">
                                   <span>{{item.createTime * 1000 | formatDate}}</span>
                                </p>
                            </div>
                        </div>
                    </router-link>
                </li>
            </ul>
            <div v-else><img src="../assets/images/暂无订单.png" alt="暂无订单"></div>
        </mt-tab-container-item>
        <mt-tab-container-item id="2">
            <ul class="mui-table-view mui-table-view-chevron" v-if="cancel && cancel.length>0">
                <li class="mui-table-view-cell mui-media" v-for="(item,index) in cancel" :key="index">
                    <router-link class="mui-navigate-right" :to="'/orderDetail/'+item.orderId">
                        <div>
                            <span>订单号：{{item.orderId}}</span>
                            <span class="orderStatus">{{item.orderStatusStr}}</span>
                        </div>
                        <div class="mui-row">
                            <div class="mui-col-xs-3" style="padding: 0 2% 0 0">
                                <!-- <img  class="head-img" :src="website+item.goods.productPath"> -->
                                <img class="head-img" :src="require('../assets/images/'+item.orderDetailList[0].productIcon)" alt="">
                            </div>
                            <div class="mui-media-body mui-col-xs-9" style="margin: 0;padding: 0">                               
                                <!-- {{ item.orderDetailList[0].productName}} -->
                                <p class="nameList">
                                    <span v-for="(sonItem,i) in item.orderDetailList" :key="i">
                                    {{sonItem.productName}} +
                                    </span>
                                </p>  
                                <span style="float: right">￥ <span class="price">{{item.orderAmount}}</span></span>
                                <p class='mui-ellipsis'>
                                共 {{item.orderDetailList.length}} 件
                                </p>
                                <p class="orderTime">
                                   <span>{{item.createTime * 1000 | formatDate}}</span>
                                </p>
                            </div>
                        </div>
                    </router-link>
                </li>
            </ul>
            <div v-else><img src="../assets/images/暂无订单.png" alt="暂无订单"></div>
        </mt-tab-container-item>
        <mt-tab-container-item id="3">
            <ul class="mui-table-view mui-table-view-chevron" v-if="evaluate && evaluate.length>0">
                <li class="mui-table-view-cell mui-media" v-for="(item,index) in evaluate" :key="index">
                    <router-link class="mui-navigate-right" :to="'/orderDetail/'+item.orderId">
                        <div>
                            <span>订单号：{{item.orderId}}</span>
                            <span class="orderStatus">{{item.orderStatusStr}}</span>
                        </div>
                        <div class="mui-row">
                            <div class="mui-col-xs-3" style="padding: 0 2% 0 0">
                                <!-- <img  class="head-img" :src="website+item.goods.productPath"> -->
                                <img class="head-img" :src="require('../assets/images/'+item.orderDetailList[0].productIcon)" alt="">
                            </div>
                            <div class="mui-media-body mui-col-xs-9" style="margin: 0;padding: 0">                               
                                <!-- {{ item.orderDetailList[0].productName}} -->
                                <p class="nameList">
                                    <span v-for="(sonItem,i) in item.orderDetailList" :key="i">
                                    {{sonItem.productName}} +
                                    </span>
                                </p>  
                                <span style="float: right">￥ <span class="price">{{item.orderAmount}}</span></span>
                                <p class='mui-ellipsis'>
                                共 {{item.orderDetailList.length}} 件
                                </p>
                                <p class="orderTime">
                                   <span>{{item.createTime * 1000 | formatDate}}</span>
                                </p>
                            </div>
                        </div>
                    </router-link>
                </li>
            </ul>
            <div v-else><img src="../assets/images/暂无订单.png" alt="暂无订单"></div>
        </mt-tab-container-item>
        <mt-tab-container-item id="4">
            <ul class="mui-table-view mui-table-view-chevron" v-if="done && done.length>0">
                <li class="mui-table-view-cell mui-media" v-for="(item,index) in done" :key="index">
                    <router-link class="mui-navigate-right" :to="'/orderDetail/'+item.orderId">
                        <div>
                            <span>订单号：{{item.orderId}}</span>
                            <span class="orderStatus">{{item.orderStatusStr}}</span>
                        </div>
                        <div class="mui-row">
                            <div class="mui-col-xs-3" style="padding: 0 2% 0 0">
                                <img class="head-img" :src="require('../assets/images/'+item.orderDetailList[0].productIcon)" alt="">
                            </div>
                            <div class="mui-media-body mui-col-xs-9" style="margin: 0;padding: 0">                               
                                <!-- {{ item.orderDetailList[0].productName}} -->
                                <p class="nameList">
                                    <span v-for="(sonItem,i) in item.orderDetailList" :key="i">
                                    {{sonItem.productName}} +
                                    </span>
                                </p>  
                                <span style="float: right">￥ <span class="price">{{item.orderAmount}}</span></span>
                                <p class='mui-ellipsis'>
                                共 {{item.orderDetailList.length}} 件
                                </p>
                                <p class="orderTime">
                                   <span>{{item.createTime * 1000 | formatDate}}</span>
                                </p>
                            </div>
                        </div>
                    </router-link>
                </li>
            </ul>
            <div v-else><img src="../assets/images/暂无订单.png" alt="暂无订单"></div>
        </mt-tab-container-item>
    </mt-tab-container>
  </div>
</template>
 
<script>
import { MessageBox } from 'mint-ui';
import {Toast} from 'mint-ui';
import {detetimeFormat} from '../common/js/datetimeFormat';
import {formatDate} from '../common/js/filters.js'
export default {
    name: 'orderPage',
    data() {
        return {
            orderList:[],
            wait:[],//等待送达
            cancel:[],//取消
            evaluate:[], //待评价
            done:[],//已完成
            selected: '1',
        };
    },
    methods: {
    },
    filters:{
    formatDate(time){
        var data = new Date(time);
        return formatDate(data,'yyyy-MM-dd');
    }
   },
  created() {
     //从localStorage中获取buyerId
    var buyerId = localStorage.getItem("buyerId");
    if(!buyerId) {
        Toast("请先登录!")
        this.$router.push('/login')
    }else {
        //获取Orders的ajax请求
        this.axios.get("/buyer/order/list?buyerid="+ buyerId).then(result => {
            this.orderList = result.data.data;

        });

        this.axios.get("/buyer/order/listByStatus?buyerid="+ buyerId + "&orderStatus=1").then(result => {
            this.wait = result.data.data;
        })        

        this.axios.get("/buyer/order/listByStatus?buyerid="+ buyerId + "&orderStatus=2").then(result => {
            this.cancel = result.data.data;
        });

        this.axios.get("/buyer/order/listByStatus?buyerid="+ buyerId + "&orderStatus=3").then(result => {
            this.evaluate = result.data.data;
        });

        this.axios.get("/buyer/order/listByStatus?buyerid="+ buyerId + "&orderStatus=4").then(result => {
            this.done = result.data.data;
        });
    }
  },
  mounted() {

  },

};
</script>
 
<style lang="css" scoped>
    .mainDiv {
        padding: 10px;
    } 
    .itemBox {
        display: flex;
        padding-top: 10px;
        border-bottom: 1px solid gray;
    }
    .itemBox p {
        flex: 1;
        line-height: 50px;
    }
    .itemBox p img {
        height: 50px;
        width: 50px;
    }
      .mui-media-body{
    font-size: 14px;
  }
  .mui-ellipsis{
    font-size: 12px;
  }

  .price{
  color: red;
  font-size: 18px;
  font-weight: bold;
}
  a:after,a:link,a:active,a:visited{
    text-decoration:none;
  }
  .orderTime{
    color: #c8cbcf;
    font-size: 12px;
  }
  .head-img{
    width: 60px;
    height: 58px;
  }
  .orderStatus{
    display: inline-block;
    font-size: 12px;
    float: right;
    color: cornflowerblue;
  }
  .nameList {
      /* display: inline; */
      text-overflow:ellipsis;
  }
</style>