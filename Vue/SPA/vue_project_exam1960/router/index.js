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
    path: '/about',
    name: 'About',
    component: () => import('@/views/About.vue')
  },
  ///categoryIndex
  {
    path: '/categoryIndex',
    name: 'categoryIndex',
    component: () => import('@/views/categoryIndex.vue')
  },
  //bookIndex
  {
    path: '/bookIndex',
    name: 'bookIndex',
    component: () => import('@/views/bookIndex.vue')
  },
  //addCategory
  {
    path: '/addCategory',
    name: 'addCategory',
    component: () => import('@/views/addCategory.vue')
  },
  //editcategory
  {
    path: '/editcategory/:cid',
    name: 'editcategory',
    component: () => import('@/views/editcategory.vue')
  },
  //editBook/
  {
    path: '/editBook/:bid',
    name: 'editBook',
    component: () => import('@/views/editBook.vue')
  },
  //addBook
  {
    path: '/addBook',
    name: 'addBook',
    component: () => import('@/views/addBook.vue')
  },
  //login
  {
    path: '/login',
    name: 'login',
    component: () => import('@/views/login.vue')
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
