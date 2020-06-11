<template>
  <div class="cartcontroll">
    <transition name="slide-fade">
      <div class="decrease icon-remove_circle_outline" v-show="food.count" @click="decreaseCart">-</div>
    </transition>
    <div class="count" v-show="food.count">{{food.count}}</div>
    <div class="add icon-add_circle" @click="addCart">+</div>
  </div>
</template>

<script>
  import Vue from 'vue'
  import { eventBus } from '../event-bus'


  export default {
     //父组件传过来的，接收一个props属性来计算商品的个数,food.count,去goods组件中引入cartcontrol
    props: {
      food: {
        type: Object
      }
    },
    methods: {
      addCart (e) {
         //解决PC端双点击的问题
        if (!e._constructed) { //浏览器直接return掉,去掉自带click事件的点击
                return;
        }
        if (!this.food.count) {
          Vue.set(this.food, 'count', 1)//给this.food增加一个count属性，并初始化为1
        } else {
          this.food.count += 1
        }
        // 优化体验，异步传递当前点击文档节点
        this.$nextTick(() => {
          eventBus.$emit('addcart', e.target)
        })
        console.log(this.food.count);
      },
      decreaseCart (e) {
        //解决PC端双点击的问题
        if (!e._constructed) { //浏览器直接return掉,去掉自带click事件的点击
            return;
        }
        if (this.food.count) {
          this.food.count --
        }
      }
    }
  }
</script>

<style lang="stylus" rel="stylesheet/stylus">
  .cartcontroll
    font-size: 0
    .decrease,.add
      display: inline-block
      font-size: 24px
      width: 24px
      height: 24px
      color: rgb(0,160,220)
      padding: 6px
    .count
      display: inline-block
      vertical-align: top
      font-size: 10px
      width: 12px
      line-height: 24px
      padding-top: 6px
      text-align: center
      color: rgb(143,153,159)
  .cartcontroll
  &.slide-fade-enter-active
    transition: all .3s ease
  &.slide-fade-leave-active
    transition: all .3s cubic-bezier(1.0, 0.5, 0.8, 1.0)
  &.slide-fade-enter, &.slide-fade-leave-to
    /* .slide-fade-leave-active for below version 2.1.8 */
    transform: translateX(24px) rotate(360deg)
    opacity: 0

</style>
    
