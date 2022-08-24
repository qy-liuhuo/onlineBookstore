import { createApp } from 'vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import App from './App.vue'
import qs from 'qs';
import axios from "axios";
const app = createApp(App)
axios.defaults.baseURL = 'api';
app.config.globalProperties.axios = axios
app.config.globalProperties.qs = qs;
app.use(ElementPlus)
app.mount('#app')
