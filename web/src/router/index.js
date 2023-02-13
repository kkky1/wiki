import VueRouter from "vue-router";
import home from '@/views/Home'
import content from '@/views/MyContent'
const router = new VueRouter({
    routes: [
        {
            path: '/',
            redirect: '/home'
        },
        {
            path:'/home',
            component:home
        },
        {
            path: '/content',
            component: content
        }
    ]
})

export default router