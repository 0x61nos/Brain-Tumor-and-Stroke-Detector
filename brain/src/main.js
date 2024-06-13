import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import { VuelidatePlugin } from '@vuelidate/core'
import 'bootstrap/dist/css/bootstrap.min.css';
import '@fortawesome/fontawesome-free/css/all.css'; // For CSS version
// OR
import '@fortawesome/fontawesome-free/js/all.js'; // For SVG version
import './axios.js'



createApp(App).use(VuelidatePlugin).use(store).use(router).mount('#app')
