<template>
  <v-app>
    <v-app-bar
      app
      light
      outlined
      elevation="0"
    >
      <v-toolbar-title>Home Index</v-toolbar-title>
      <v-spacer />
      <v-toolbar-title v-if="username !== {}" class="text-subtitle-1">{{ `${username.firstName} ${username.lastName}` }}</v-toolbar-title>
    </v-app-bar>
    <v-main>
      <router-view/>
    </v-main>
  </v-app>
</template>

<script lang="ts">
  import axios from 'axios';
import { Vue, Component } from 'vue-property-decorator'

  @Component
  export default class App extends Vue {

    username = {};

    async mounted() {
      try {
        const response = await axios.get('/user-account/testuser')
        this.username = response.data;
      } catch (e) {
        console.log(e)
      }
    }

  }
</script>
