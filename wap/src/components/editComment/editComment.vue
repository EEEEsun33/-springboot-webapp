<template>
    <div class="Box">
        <!-- <div class="rating-right">
            <div class="score-wrapper">
                <span class="title">服务态度</span>
                <star :size="24" :score="5"></star>
                <span class="score">4.5</span>
            </div>
            <div class="score-wrapper">
                <span class="title">商品评分</span>
                <star :size="24" :score="5"></star>
                <span class="score">4.5</span>
            </div>
            <div class="delivery-wrapper">
                <span class="title">送达时间</span>
                <span class="time">30分钟</span>
            </div>
        </div> -->
        <textarea name="comment" id="commentTxt" cols="30" rows="5" placeholder="为了更好的服务顾客，请提出您宝贵的建议" v-model="content"></textarea>
        <mt-button id="postBtn" type="primary" size="big" @click="postComment">提交评价</mt-button>
    </div>
</template>

<script>
import star from '../../components/star/star.vue'
import store from "@/store/index";
import {Toast} from 'mint-ui';
export default {
    name: 'editComment',
    store,
    data(){
        return {
            orderId,
            content:null,
        }
    },
    created(){
        this.orderId = this.$route.query.orderId;
    
    },
    methods: {
        postComment(){
            let param = new URLSearchParams()
            param.append('buyerid', this.$store.state.buyerId)
            param.append('orderId', this.orderId)
            param.append('username', this.$store.state.account)
            param.append('content', this.content)
            this.axios.post("/comment",param).then(response =>{
                console.log(response.data.data);
                if(response.data.code == 0 ){
                    Toast("评价提交成功!");
                    this.$router.push('/orderPage');

                }else {
                    Toast("提交失败!")
                }
            })
        }
    },
    components: {
        star
    }

}
</script>

<style lang="stylus" scoped>
.Box {
    padding: 10px 10px;
}
#commentTxt {
    padding: 0;
}
#postBtn {
    width: 100%;
}
</style>