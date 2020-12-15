
<template>
  <div id="app">
    <div id="card">
      <span>
      <router-view name="header" />
      </span>
      <span>
      <router-view name="menu" />
      </span>
      <span>
      <router-view/>
      </span>
      <span class='footer'>
      <router-view name="footer" />
      </span>
    </div>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style lang="scss" >

@import url('https://use.fontawesome.com/releases/v5.8.1/css/all.css');
@import url('https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css');

  #card{
    min-height: 100vh;
    background-size: cover;
    // font-family: ;
  }
  #card{
    background: url('./assets/background.png') center center;

  }
</style>

