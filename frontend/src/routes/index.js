import Vue from 'vue'
import Router from 'vue-router'
import Chat from '../pages/chat'
import ws from '../pages/ws'

Vue.use(Router)

let router = new Router({
  mode: 'history',
  routes: [
    {
      path: '/chat',
      component: Chat
    },
    {
      path: '/ws',
      component: ws
    },
  ]
})

export default router
