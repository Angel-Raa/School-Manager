import 'semantic-ui-css/semantic.min.css'
import './assets/main.css'

import 'mosha-vue-toastify/dist/style.css'
import { createApp } from 'vue'
import { createPinia } from 'pinia'
import moshaToast from 'mosha-vue-toastify'

import App from './App.vue'
import router from './router'

const app = createApp(App)
app.use(moshaToast)
app.use(createPinia())
app.use(router)

app.mount('#app')
