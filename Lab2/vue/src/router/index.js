import Vue from 'vue'
import Router from 'vue-router'

// import HelloWorld from '@/components/HelloWorld'
import User from '@/components/User'
import Person from '@/components/Person'
import Layout from '@/components/Layout'

Vue.use(Router)

const router = new Router({
  routes: [
    // {
    //   path: '/',
    //   name: 'HelloWorld',
    //   component: HelloWorld
    // },

    {
      path: '/',
      name: 'Home',
      component: Layout,
      redirect: '/user',
      children: [{
        path: 'user',
        component: User
        // component: () => {
        //   import('@/components/User')
        // }
      },
      {
        path: 'person',
        component: Person
        // component: () => {
        //     import('@/components/Person')
        // }
      }]
    }

    // {
    //   path: '/user',
    //   name: 'user',
    //   component: User
    // },
    // {
    //   path: '/person',
    //   name: 'Person',
    //   component: Person
    // }
  ]
})

// router.beforeEach((to, from, next) => {
//   console.log('前置路由守卫')
//   next()
// })

export default router
