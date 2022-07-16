import Vue from 'vue'
import Router from 'vue-router'

import User from '@/components/User'
import Person from '@/components/Person'
import Layout from '@/components/Layout'

Vue.use(Router)

const router = new Router({
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Layout,
      redirect: '/user',
      children: [{
        path: 'user',
        component: User
      },
      {
        path: 'person',
        component: Person
      }]
    }
  ]
})

export default router
