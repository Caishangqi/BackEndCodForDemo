import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../components/Login.vue'
import ElementUI from '../components/ElementUI.vue'
// 别忘了导入Home组件
import Home from '../components/Home.vue'
import User from '../components/user/user.vue'
import ItemCat from '../components/items/ItemCat.vue'
//使用路由机制
Vue.use(VueRouter)
const routes = [
  {path: '/', redirect: '/login'},
  {path: '/login', component: Login},
  {path: '/elementUI', component: ElementUI},
  {
    path: '/home', component: Home, children: [
      {path: '/user', component: User},
      {path: '/itemCat', component: ItemCat}]
  }

]

//路由导航守卫!!!!!!!

const router = new VueRouter({

  routes

})

export default router
