<template>
    <div class="app-container">
        <h2 v-html="noticeTitle"></h2>
        <p v-html="createTime"></p>
      <div id="content" v-html="noticeContent">

      </div>
      </div>
</template>

<script>
    export default {
      name: "notice",
      data(){
          return {
            noticeId: this.$route.params.noticeId,
            noticeTitle: null,
            createTime:null,
            noticeContent: null
          }
      },
      created(){
        this.axios.get("/notice/oneNotice?noticeId="+this.noticeId).then(result=>{
            this.noticeTitle=result.data.data.noticeTitle;
            this.createTime=result.data.data.createTime;
            this.noticeContent=result.data.data.noticeContent;
        })
      }
    }
</script>

<style scoped>
  .app-container{
    padding: 5px 10px;
  }
  h4{
    text-align: center;
  }
</style>