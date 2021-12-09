import Vue from 'vue'
import App from './App.vue'
import router from './router'
import './plugins/element.js'
import './assets/css/global.css'
import './assets/ali-icon/iconfont.css'
/* 导入富文本编辑器 */
import VueQuillEditor from 'vue-quill-editor'

/* 导入富文本编辑器对应的样式 */
import 'quill/dist/quill.core.css' // import styles
import 'quill/dist/quill.snow.css' // for snow theme
import 'quill/dist/quill.bubble.css' // for bubble theme
/* 导入axios包 */
import axios from 'axios'
/* 设定axios的请求根目录 */
axios.defaults.baseURL = 'http://localhost:8091/'
/* 向vue对象中添加全局对象 以后发送ajax请求使用$http对象 */
/* 父组件将参数传递给子组件 需要声明 prototype.key= xxx */
Vue.prototype.$http = axios

Vue.config.productionTip = false

/* 定义过滤器 */
Vue.filter("priceFormat", function (price) {

  return (price / 100).toFixed(2)
})

/* 将富文本编辑器注册为全局可用的组件 */
Vue.use(VueQuillEditor)


new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
