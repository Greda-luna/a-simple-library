import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from "@/views/Home/HomeView.vue";
import Layout from "@/views/Layout.vue";

Vue.use(VueRouter)

const routes = [

  {
    path: '/login',
    name: 'Login',
    component: () => import('../views/login/Login.vue')
  },
  {
    path: '/',
    name: 'layout',
    component: () =>import('../views/Layout.vue'),
    redirect:'/home',
    children: [
      {
        path: 'home',
        name: 'Home',
        component: () => import('../views/Home/HomeView.vue')
      },
      {
        path: 'userlist',
        name: 'UserList',
        component: () => import('../views/user/List.vue')
      },
      {
        path: 'adduser',
        name: 'AddUser',
        component: () => import('../views/user/Add.vue')
      },
      {
        path: 'edituser',
        name: 'EditUser',
        component: () => import('../views/user/Edit.vue')
      },
      //Admin部分
      {
        path: 'adminlist',
        name: 'AdminList',
        component: () => import('../views/admin/List.vue')
      },
      {
        path: 'addadmin',
        name: 'Addadmin',
        component: () => import('../views/admin/Add.vue')
      },
      {
        path: 'editadmin',
        name: 'EditAdmin',
        component: () => import('../views/admin/Edit.vue')
      },
      //Category部分
      {
        path: 'categorylist',
        name: 'CategoryList',
        component: () => import('../views/category/List.vue')
      },
      {
        path: 'addcategory',
        name: 'AddCategory',
        component: () => import('../views/category/Add.vue')
      },
      {
        path: 'editcategory',
        name: 'EditCategory',
        component: () => import('../views/category/Edit.vue')
      },
    ]
  },


/*
  component: () => import(/!* webpackChunkName: "about" *!/ '../views/HomeView.vue')
*/

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
