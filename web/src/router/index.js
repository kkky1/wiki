import VueRouter from "vue-router";
import home from '@/views/Home'
import content from '@/views/MyContent'
import about from '@/views/About'
import adminBook from '@/views/Admin/AdminBook'
import CategoryBook from '@/views/Admin/CategoryBook'
import AdminUser from '@/views/Admin/AdminUser'
import doc from '@/views/Admin/DocBook'
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
        },
        {
            path:'/adminCategory',
            component:CategoryBook
        },
        {
            path:'/adminUser',
            component:AdminUser
        },
        {
            path:'/doc',
            component:doc
        }
    ]
})

export default router