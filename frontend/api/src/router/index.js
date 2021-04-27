import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/ImportacaoCodeList',
    name: 'ImportacaoCodeList',
    component: () => import(/* webpackChunkName: "about" */ '../views/ImportacaoCodeList/ImportacaoCodeList.vue')
  },
  {
    path: '/EdiçãoCodeList',
    name: 'EdiçãoCodeList',
    component: () => import(/* webpackChunkName: "about" */ '../views/EdiçãoCodeList/EdiçãoCodeList.vue')
  },
  {
    path: '/docsearch',
    name: 'docsearch',
    component: () => import('../views/docsearch/docsearch.vue')
  }
  // {
  //   path: '/about',
  //   name: 'About',
  //   // route level code-splitting
  //   // this generates a separate chunk (about.[hash].js) for this route
  //   // which is lazy-loaded when the route is visited.
  //   component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  // }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
