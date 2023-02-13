import VueRouter from "vue-router";
import home from '@/views/Home'
import content from '@/views/MyContent'
import about from '@/views/About'
import adminBook from '@/views/Admin/AdminBook'
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
        },
        {
            path:'/about',
            component: about
        },
        {
            path: '/adminBook',
            component: adminBook
        }
    ]
})

export default router