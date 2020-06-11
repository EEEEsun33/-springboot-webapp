<template>
  <div class="goods">
     <div class="menu-wrapper" ref="menuWrapper">
       <ul>
         <li v-for="(item,index) in goods" class="menu-item" :class="{'current': currentIndex === index}" @click="selectMenu(index)" :key="index">
           <span class="text border-1px">
             {{item.name}}</span>
         </li>
       </ul>
     </div>
     <div class="foods-wrapper" ref="foodsWrapper">
       <ul>
         <li v-for="item in goods" class="food-list food-list-hook" :key="item.id">
           <h1 class="title">{{item.name}}</h1>
           <ul v-for="food in item.foods" class="food-item border-1px">
             <div class="icon" @click="selectFood(food)">
               <img width="57" height="57" :src="require('../../assets/images/'+food.icon)">
             </div>
             <div class="content">
               <h2 class="name">{{food.name}}</h2>
               <p class="description">{{food.description}}</p>
               <div class="extra">
                 <span class="count">月售{{food.sellCount}}份</span>
                 <!-- <span class="rating">好评率{{food.rating}}%</span> -->
               </div>
               <div class="price">
                 <span class="now">￥{{food.price}}</span>
                 <!-- <span v-show="food.oldPrice" class="old">￥{{food.oldPrice}}</span> -->
               </div>
               <div class="cart-wrapper">
                 <cartcontrol :food="food"></cartcontrol>
               </div>
             </div>
           </ul>
         </li>
         <div style="height:30px;width:100%"></div>
       </ul>
     </div>
     <shopcart :select-foods="selectFoods"></shopcart>
  </div>
</template>

<script>
import BScroll from 'better-scroll'
import shopcart from '../../components/shopcart/shopcart'
import cartcontrol from '../../components/cartcontrol/cartcontrol'
// import axios from 'axios'

const ERR_OK = 0
const debug = process.env.NODE_ENV !== 'production'

export default {
  // 使用`props`属性要定义父组件传递过来的数据
  props: {
    seller: {
      type: Object
    }
  },
  data () {
    return {
      goods: [],
      listHeight: [],
      scrollY: 0,
      selectedFood: {}
    }
  },
  computed: {
    currentIndex () {
      for (let i = 0; i < this.listHeight.length; i++) {
        let height1 = this.listHeight[i]
        let height2 = this.listHeight[i + 1]
        if (!height2 || (this.scrollY >= height1 && this.scrollY < height2)) {
          return i
        }
      }
      return 0
    },
    selectFoods () {
      let foods = []
      this.goods.forEach((good) => {
        good.foods.forEach((food) => {
          if (food.count) {
            foods.push(food)
          }
        })
      })
      return foods
    }
  },
  // created: 实例已经在内存中创建完成；此时 data 和 methods 已经创建完成，此时还没有开始编译模板
  
  created () {
             //  请求商品数据
             this.axios.get("/buyer/product/list").then(result=>{
              this.goods = result.data.data
                     this.$nextTick(() => {
                      this._initScroll()
                })

             })
  },
  mounted () {
    setTimeout(() => {
      this._initScroll()
      this._calculateHeight()
    }, 20)
  },
  methods: {
    _initScroll () {
      this.menuScroll = new BScroll(this.$refs.menuWrapper, {
        click: true
      })
      // 判断better-scroll是否存在，存在就不在创建了，如果不判断的话就会出现事件多次执行的BUG
      if(!this.foodsScroll){
        this.foodsScroll = new BScroll(this.$refs.foodsWrapper, {
        click: true,
        probeType: 3
      })
      }else{
        this.foodsScroll.refresh
      }
      this.foodsScroll.on('scroll', (pos) => {
        this.scrollY = Math.abs(Math.round(pos.y))
      })
    },
    _calculateHeight () {
      let foodList = this.$refs.foodsWrapper.getElementsByClassName('food-list-hook')
      let height = 0
      this.listHeight.push(height)
      for (let i = 0; i < foodList.length; i++) {
        let item = foodList[i]
        height += item.clientHeight
        this.listHeight.push(height)
      }
    },
    selectMenu (index) {
      let foodList = this.$refs.foodsWrapper.getElementsByClassName('food-list-hook')
      let ref = foodList[index]
      this.foodsScroll.scrollToElement(ref, 300)
    },
    selectFood (food) {
      this.selectedFood = food
      this.$refs.food.show()
    }
  },
  components: {
    shopcart,
    cartcontrol
  }
}
</script>

<style lang="stylus" rel="stylesheet/stylus">
  @import '../../common/stylus/mixin.styl'
  ul,li{ padding:0;margin:0;list-style:none}

  .goods
    position: absolute
    top: 40px
    bottom: 44px
    width: 100%
    max-width: 540px;
    display: flex
    overflow: hidden
    .menu-wrapper
      flex: 0 0 80px
      background: #f3f5f7
      .menu-item
        width: 80px
        height: 54px
        display: table
        font-size: 12px
        line-height: 14px
        padding: 0 12px
        font-weight: 200
        &:last-child
          .text:after
            border-top: none
        &.current
          position: relative
          background: #fff
          font-weight: 700
          margin-top: -1px
          z-index: 100
          .text:after
            border-top: 0
        .text
          display: table-cell
          vertical-align: middle
          border-1px(rgba(7,17,27,.1))
          width: 56px
          font-size: 12px
          .icon
            vertical-align: top
            display: inline-block
            width: 12px
            height: 12px
            margin-right: 4px
            background-size: 12px 12px
            background-repeat: no-repeat
            &.decrease
              bg-image('decrease_3')
            &.discount
              bg-image('discount_3')
            &.guarantee
              bg-image('guarantee_3')
            &.invoice
              bg-image('invoice_3')
            &.special
              bg-image('special_3')
    .foods-wrapper
      flex: 1
      .title
        padding-left: 14px
        border-left: 2px solid #d9dde1
        font-size: 12px
        height: 26px
        line-height: 26px
        color: rgb(147,153,159)
        background-color: #f3f5f7
      .food-item
        display: flex
        margin: 18px
        padding-bottom: 8px
        border-1px(rgba(7,17,27,.1))
        &:last-child:after
          border-top: 0
        .icon
          flex: 0 0 57px
          margin-right: 10px
        .content
          flex: 1
          .name
            margin: 2px 0 8px 0
            height: 14px
            line-height: 14px
            font-size: 14px
            color: rgb(7,17,27)
          .description,.extra
            font-size: 10px
            line-height: 10px
            color: rgb(147,153,159)
            margin-bottom: 8px
          .extra
              .count
                margin-right: 12px
                line-height: 10px
              .rating
                line-height: 10px
          .price
            .now
              font-size: 14px
              color: rgb(240,20,20)
              font-weight: 700
              line-height: 24px
              margin-right: 8px
            .old
               font-size: 10px
               text-decoration: line-through
               color: rgb(147,153,159)
               font-weight: 700
               line-height: 24px
          .cart-wrapper
            position: absolute
            right: 0
            bottom: 12px
</style>
