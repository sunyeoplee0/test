import { createRouter, createWebHistory } from 'vue-router'
import BoardListView from '@/views/BoardListView.vue'
import BoardDetailView from '@/views/BoardDetailView.vue'
import BoardRegView from '@/views/BoardRegView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    { path: '/', redirect: '/board/list' },
    { path: '/board/list', name: 'board-list', component: BoardListView },
    { path: '/board/reg', name: 'board-reg', component: BoardRegView },
    { path: '/board/:idx', name: 'board-detail', component: BoardDetailView, props: true },
  ],
})

export default router
