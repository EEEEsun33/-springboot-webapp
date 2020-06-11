<template>
    <div class="Box">
        <ul class="mui-table-view mui-table-view-chevron">
            <li class="mui-table-view-cell mui-media" v-for="(comment,index) in commentList" :key="index">
                <div class="liTitle">
                    <span class="buyerName">{{userName}}</span>
                    <span class="time">{{comment.createTime}}</span>
                </div>      
                <div class="contentDiv">{{comment.commentContent}}</div>
            </li>
        </ul>
        
    </div>
</template>

<script>
import store from "@/store/index";
    export default {
        name: 'comment',
        store,
        data() {
            return {
                buyerId:null,
                userName:null,
                commentList:[]
            }
            
        },
        created() {
            this.buyerId = this.$store.state.buyerId;
            // this.buyerId = localStorage.getItem("buyerId");
            this.userName = this.$store.state.account;

            this.axios.get("/userCommentList",{
                params: {
                   buyerid: this.buyerId
                }
            }).then(response =>{
                console.log(response.data.data);
                this.commentList = response.data.data;
                this.commentList.forEach(comment => {
                    console.log(comment);
                });
                // console.log(this.commentList[0]);
            })
        }
    }
</script>

<style lang="stylus" scoped>
.Box {
    padding: 10px 0px;
}
.buyerName {
    color: #26a2ff;
    margin-right:100px;
}
.time {
    color: #bbbbbb;
}
.contentDiv {
    padding-top: 15px;
}
.mui-table-view-cell {
    padding-right:0;
}
</style>