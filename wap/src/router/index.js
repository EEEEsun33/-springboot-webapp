import Vue from 'vue'
import Router from 'vue-router'
import Index from '@/components/Index'
import Cart from '@/components/Cart'
import Mine from '@/components/Mine'
import Login from '@/components/mine/Login'
import Notice from '@/components/Home/Notice'
import Wallet from '@/components/Mine/Wallet'
import Info from '@/components/Mine/Info'
import ModifyPwd from '@/components/Mine/ModifyPwd'
import Advice from '@/components/Mine/Advice'
import About from '@/components/Mine/About'
import Test from '@/components/Cart/Test'
// import OrderDetail from '@/components/order/OrderDetail'
import goods from '@/components/goods/goods'
import confirmOrder from '@/components/confirmOrder/confirmOrder'
import orderPage from '@/components/orderPage'
import register from '@/components/mine/register'
import comments from '@/components/mine/comments'
import orderDetail from '@/components/orderDetail/orderDetail'
import editComment from '@/components/editComment/editComment'
import notice from '@/components/notice/notice'
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Index',
      component: Index,
      meta: {
        keepAlive: true // 需要缓存
      }
    },
    {
      path: '/notice/:noticeId',
      name: 'notice',
      component: notice,
      meta: {
        keepAlive: false
      }
    },
    {
      path: '/orderPage',
      name:'orderPage',
      component: orderPage,
      meta: {
        keepAlive: false // 需要缓存,下单后记得刷新下当前页面
      }
    },
    {
      path: '/orderDetail/:orderId',
      name: 'orderDetail',
      component: orderDetail,
      meta: {
        keepAlive: false
      }
    },
    {
      path: '/goods',
      name: 'Goods',
      component: goods
    },
    {
      path: '/confirmOrder',
      name: 'confirmOrder',
      component: confirmOrder,
      meta: {
        keepAlive: false
      }
    },
    {
      path: '/cart',
      name: 'Cart',
      // component: goods,
      component: Cart,
      meta: {
        keepAlive: false // 不需要缓存
      }
    },
    {
      path: '/mine',
      name: 'Mine',
      component: Mine,
      meta: {
        // keepAlive: true // 需要缓存
        keepAlive: false // 不需要缓存
      }
    },
    {
      path: '/login',
      name: 'Login',
      component: Login,
      meta: {
        // keepAlive: true // 需要缓存
        keepAlive: false // 不需要缓存
      }
    },
    {
      path: '/register',
      name: 'register',
      component: register
    },
    {
      path: '/comments',
      name: 'comment',
      component: comments
    },
    {
      path: '/editComment',
      name: 'editComment',
      component: editComment
    },
    {
      path: '/notice/:newsId',
      name: 'Notice',
      component: Notice,
      meta: {
        keepAlive: false // 不需要缓存,每个notice显示的内容不同
      }
    },
    {
      path: '/wallet',
      name: 'Wallet',
      component: Wallet,
      meta: {
        keepAlive: false // 不需要缓存
      }
    },
    {
      path: '/info',
      name: 'Info',
      component: Info,
      meta: {
        keepAlive: true // 需要缓存
      }
    },
    {
      path: '/modifypwd',
      name: 'ModifyPwd',
      component: ModifyPwd,
      meta: {
        keepAlive: true // 需要缓存，修改完信息后记得刷新下当前页面
      }
    },
    {
      path: '/advice',
      name: 'Advice',
      component: Advice,
      meta: {
        keepAlive: true // 需要缓存
      }
    },
    {
      path: '/about',
      name: 'About',
      component: About,
      meta: {
        keepAlive: true // 需要缓存
      }
    },
    {
      path: '/test',
      name: 'Test',
      component: Test,
      meta: {
        keepAlive: false // 不需要缓存
      }
    },
    // {
    //   path: '/orderDetail/:orderId',
    //   name: 'OrderDetail',
    //   component: OrderDetail,
    //   meta: {
    //     keepAlive: false // 不需要缓存
    //   }
    // }
  ],
  linkActiveClass: 'mui-active'
})
