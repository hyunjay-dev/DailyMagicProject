import Vue from 'vue'
import './plugins/axios'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify';

// import { fontAwesomeIcon } from '@/fontAwesomeIcon.js';

Vue.config.productionTip = false

new Vue({
    router,
    store,
    vuetify,
    // fontAwesomeIcon,
    render: h => h(App)
}).$mount('#app')