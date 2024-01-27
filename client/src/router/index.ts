import { createRouter, createWebHistory, type RouteRecordRaw, type Router } from 'vue-router'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'home',
    component: () => import('@/views/HomeViews.vue')
  },
  {
    path: '/profile',
    name: 'profile',
    component: () => import('@/views/ProfileViews.vue')
  }
]

const router: Router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
})

export default router
