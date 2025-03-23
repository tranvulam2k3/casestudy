import { createRouter, createWebHistory } from 'vue-router';
import bookListRouter from './bookListRouter';
import Home from '@/views/Home.vue';

const routes = [
  bookListRouter,
  // Route trang chủ
  {
    path: '/',
    name: 'Home',
    component: Home
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;