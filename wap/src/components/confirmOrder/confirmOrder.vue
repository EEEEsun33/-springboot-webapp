<template>
<div>
    <div class="userMsg">
        <mt-field label="联系人" :placeholder="userName" v-model="userName"></mt-field>
        <mt-field label="联系电话" :placeholder="userPhone" type="tel" v-model="userPhone"></mt-field>
        <mt-field label="地址" placeholder="请输入送达地址" type="textarea" rows="4" v-model="address"></mt-field>
    </div>
    <div class="selectedFood">
        <ul>
            <li v-for="food in selectFoods" class="food">
                <div class="icon">
                    <img width="57" height="57" :src="require('../../assets/images/'+food.icon)">
                </div> 
                <span class="name">{{food.name}}</span>
                <span class="count">x{{food.count}}</span>
                <div class="price">
                    <span>￥{{food.price*food.count}}</span>
                </div>
            </li>
            <li>合计:￥{{totalPrice}}</li>
          </ul>
    </div>
    <div>
        <button id="confirmBtn" @click="createOrder">确认下单</button>
    </div>
</div>
</template>

<script>
// 引入store
import store from "@/store/index";
import VDistpicker from 'v-distpicker';
export default {
    name: "confirmOrder",
    // 注册使用
    store,
    components: {},
    data() {
        return {
            selectFoods: [],
            userName: '',
            userPhone,
            address: '',
            totalPrice
        }

    },
    created() {
        console.log(this.$store.state.account + this.$store.state.userPhone + this.$store.state.buyerId);
    
        this.selectFoods = this.$route.query.selectFoods;
        this.totalPrice = this.$route.query.totalPrice;
        this.userName = this.$store.state.account;
        this.userPhone = this.$store.state.userPhone;
                
        
    },
    mounted(){

    },
    watch:{},
    computed:{
    },
    methods: {
        createOrder(){
            let param = new URLSearchParams()
            param.append('name', this.$store.state.account)
            param.append('phone', this.$store.state.userPhone)
            param.append('address',this.address)
            param.append('buyid', this.$store.state.buyerId)

            let goods_arr = [];
            this.selectFoods.forEach(food =>{
                console.log(food);               
                let foods = new Object();
                foods.productId = food.id;
                foods.productQuantity = food.count;
                goods_arr.push(foods);
            })
            //  在向web 服务器发送数据时,数据必须是字符串。 通过JSON.stringify() 把JavaScript 对象转换为字符串。
            let goods_josn = JSON.stringify(goods_arr);

            param.append('items',goods_josn);

            this.axios.post("/buyer/order/create",param).then(response =>{
                 console.log(response.data.data);
                 let orderId = response.data.data.orderId;
                 if(response.data.code == 0){
                    this.$router.push({
                        path:"/orderDetail/" + orderId
                    });
                 }
                 else {
                     console.log("下单失败");
                 }
            })
        },      
    }

};
</script>

<style lang="stylus" scope>
    ul,li{ 
        padding:0;
        margin:0;
        list-style:none;
    }
    .food {
        border-bottom:1px solid gray;
        display:flex;
    }
    .name,.count,.price {
        flex:1;
    }

    .userMsg {
        margin: 15px;
        border: 15px solid gary;
    }
    .userMsg>input{
        border:0;
        margin:0;
    }  
    input[type='text']{
        margin-bottom 0;
    }
    input[type='tel']{
        margin-bottom 0;
    }
    .userMsg li {
        display:flex;
    }
    .userMsg>li>span {
        flex:1;
    }
    .selectedFood {
        margin: 15px;
        border-top: 1px solid gray;
        padding-top: 15px;
    }
    .selectedFood li {
        margin-bottom:15px;
        padding-bottom: 15px;
    }
    .icon {
        margin-right: 15px;
    }
    textarea {
        rows: 3;
        border: 1px solid rgba(0,0,0,0.2);
    }
    #confirmBtn{
        float:right;
        margin-right:15px;
        background-color: #26a2ff;
        color: #fff;
        border: none;
    }
</style>