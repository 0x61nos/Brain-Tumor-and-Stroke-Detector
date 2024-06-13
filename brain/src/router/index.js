import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import About from '../views/AboutView.vue'
import Login from '@/views/Login/login.vue'
import Signup from '@/views/Signup/Signup.vue'
import Services from '@/views/Services/Services'
import Doctor from '@/views/Doctor.vue'
import Dprofile from '@/views/Dprofile'
import { param } from 'jquery'
const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/login',
    name: 'login',
    component: Login
  },
  {
    path: '/signup',
    name: 'signup',
    component: Signup
  },
  {
    path: '/services',
    name: 'services',
    component: Services
  },
  {
    path: '/doctor/:id',
    name: 'doctor',
    component: Doctor,
    props: true,
  },
  {
    path: '/dprofile/:id',
    name: 'dprofile',
    component: Dprofile,
    props: true,
  },
  {
    path: '/about',
    name: 'about',
    component: About
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    // component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
