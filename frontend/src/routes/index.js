import Vue from 'vue'
import Router from 'vue-router'
import Sw1 from '../pages/sw1'
import Sw2 from '../pages/sw2'

Vue.use(Router)

let router = new Router({
  mode: 'history',
  routes: [
    {
      path: '/ws1',
      component: Sw1
    },
    {
      path: '/ws2',
      component: Sw2
    },
  ]
})

export default router
