<template>
  <v-app>
    <v-main>
      <router-view/>
    </v-main>
  </v-app>
</template>

<script>
import HelloWorld from './components/HelloWorld';
import Register from './views/Register';

export default {
  name: 'App',

  components: {
    Register,

  },

  data: () => ({
    //
  }),
};
</script>
