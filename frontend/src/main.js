import Vue from 'vue'
import App from './App.vue'
import router from './routes'
import { util } from './common'

Vue.config.productionTip = false

Vue.mixin({
  methods: {
    ...util
  }
})

new Vue({
  render: h => h(App),
  router,
}).$mount('#app')
