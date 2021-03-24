import Vue from "vue"
import VueRouter from "vue-router"
import store from "@/store"

Vue.use(VueRouter)

const rejectAuthUser = (to, from, next) => {
  if (store.state.isLogin === true) {
    alert("이미 로그인 하였습니다.")
    next("/")
  } else {
    next()
  }
}

// const onlyAuthUser = (to, from, next) => {
//   if (store.state.isLogin === false) {
//     alert("로그인이 필요합니다.")
//     next("/login")
//   } else {
//     next()
//   }
// }

const routes = [
  {
    path: "/home",
    name: "home",
    component: () => import(/* webpackChunkName: "home" */ "../views/Home.vue")
  },
  {
    path: "/login",
    name: "login",
    beforeEnter: rejectAuthUser,
    component: () =>
      import(/* webpackChunkName: "login" */ "../views/Login.vue")
  },
  {
    path: "/register",
    name: "register",
    beforeEnter: rejectAuthUser,
    component: () =>
      import(/* webpackChunkName: "register" */ "../views/Register.vue")
  }
]

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
})

export default router
