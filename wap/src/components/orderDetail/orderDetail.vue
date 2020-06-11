<template>
    <div>
        <div class="mainDiv">
            <div>
                <span>订单号：{{orderList.orderId}}</span>
                <span class="orderStatus">{{orderList.orderStatusStr}}</span>
            </div>
            <div v-for="(sonItem,i) in orderList.orderDetailList" :key="i" class="itemBox">
                <p class="iconBox"><img slot="icon" :src="require('../../assets/images/'+sonItem.productIcon)" width="24" height="24"></p>
                <p class="nameBox">{{sonItem.productName}}</p>
                <p class="priceBox"><span>￥{{sonItem.productPrice}}</span></p>
                <p class="quantityBox"><span>x{{sonItem.productQuantity}}</span></p>
            </div>
            <div class="bottomDiv">
                <span>合计：￥{{orderList.orderAmount}}</span>
                <mt-button type="primary" size="small" @click="cancelOrder(orderList.orderId)" v-if="orderStatus == '新订单已支付'">取消订单</mt-button>
                <mt-button type="primary" size="small" @click="finishOrder(orderList.orderId)" v-if="orderStatus == '新订单已支付'">完成订单</mt-button>
                <mt-button type="primary" size="small" @click="editComment(orderList.orderId)" v-if="orderStatus == '订单完成'">去评价</mt-button>
                <mt-button type="primary" size="small" @click="cancelOrder(orderList.orderId)" v-if="orderStatus == '订单取消' || orderStatus == '已评价'">删除订单</mt-button>
            </div>
        </div>       
    </div>
</template>

<script>
import { MessageBox } from 'mint-ui';
export default {
    inject: ['reload'],
    name: 'orderDetail',
    data() {
        return {
            orderId: this.$route.params.orderId,
            orderList:[],
            wait:[],//等待送达
            cancel:[],//取消
            evaluate:[], //待评价
            done:[],//已完成
            selected: '',
            orderStatus: ''
        };
    },
    methods: {
        cancelOrder(cancelOrderId){
            MessageBox.confirm('确定取消订单吗?').then(action => {
                if (action == 'confirm') {     //确认的回调
                    var buyerId = localStorage.getItem("buyerId");
                    console.log(buyerId);
                    let param = new URLSearchParams();
                    param.append('buyerid', buyerId)
                    param.append('orderId', cancelOrderId)
                    this.axios.post("/buyer/order/cancel",param).then(response =>{
                        console.log(response.data);
                        if(response.data.code == 0){
                            MessageBox.alert("订单已取消", "提示");
                            // this.reload();
                            this.$router.push('/orderPage');
                        }
                        else {
                            MessageBox.alert("操作失败，请稍后再试", "提示");
                        }
                    })
                }else{
                    console.log("取消"); 
                }
            });
        },
        editComment(orderId){
            this.$router.push({
            path:"/editComment",
            query:{
                orderId:orderId
            }
        });
        },
        finishOrder(orderId){
            MessageBox.confirm('确认订单已送达?').then(action => {
                if (action == 'confirm') {     //确认的回调
                    let param = new URLSearchParams();
                    param.append('orderId', orderId)
                    this.axios.post("/buyer/order/finish",param).then(response =>{
                        console.log(response.data);
                        if(response.data.code == 0){
                            MessageBox.alert("订单已完成", "提示");
                           this.$router.push('/orderPage');
                        }
                        else {
                            MessageBox.alert("操作失败，请稍后再试", "提示");
                        }
                    })
                }else{
                    console.log("取消"); 
                }
            });
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
        this.axios.get("/buyer/order/detail?buyerid="+ buyerId + "&orderId=" + this.orderId).then(result => {
            this.orderList = result.data.data;
            this.orderStatus = this.orderList.orderStatusStr;
            
        });

    }
  },
  mounted() {

  },

};
</script>

<style lang="stylus" scoped>
  .orderStatus{
    display: inline-block;
    font-size: 12px;
    float: right;
    color: cornflowerblue;
  }
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
    .bottomDiv {
        display:flex;
        justify-content: space-between;
        margin-top: 10px;
    }
</style>