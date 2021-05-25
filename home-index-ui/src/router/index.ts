import Vue from 'vue'
import VueRouter, { RouteConfig } from 'vue-router'
import Login from '../views/Login.vue'
import Properties from '../views/Properties.vue'
import Spaces from '../views/Spaces.vue'
import Containers from '../views/Containers.vue'

Vue.use(VueRouter)

const routes: Array<RouteConfig> = [
  {
    path: '/',
    name: 'Properties',
    component: Properties
  },
  {
    path: '/:propertyId',
    component: Spaces
  },
  {
    path: '/:propertyId/:spaceId',
    component: Containers
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
