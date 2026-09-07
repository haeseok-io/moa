import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '@/views/HomeView.vue';
import WriteView from '@/views/WriteView.vue';
import RepositoryView from '@/views/RepositoryView.vue';
import LoginView from '@/views/LoginView.vue';
import RegisterView from '@/views/RegisterView.vue';
import UserView from '@/views/UserView.vue';


const routes = [
    {
        path: '/',
        name: 'home',
        component: HomeView
    },
    {
        path: '/write',
        name: 'write',
        component: WriteView
    },
    {
        path: '/repository',
        name: 'repository',
        component: RepositoryView
    },
    {
        path: '/login',
        name: 'login',
        component: LoginView
    },
    {
        path: '/register',
        name: 'register',
        component: RegisterView
    },
    {
        path: '/@:username/:page',
        name: 'user',
        component: UserView
    }
]

const router = createRouter({
    history: createWebHistory("/"),
    routes,
})

export default router;