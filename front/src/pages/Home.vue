<template>
  <div class="main" align="center">
    <h2>Daily Magic</h2>
    <div id="main-signin">
    <router-link :to="{name:'Signin'}" class="pa-3">로그인</router-link>
    </div>
    <div id="main-signup">
    <router-link :to="{name:'Signup'}" class="pa-3">회원가입</router-link>
    </div>
    <!-- <p>{{getTime()}}</p> -->
  </div>
</template>

<script>
export default {
  name: 'Home',
  methods: {
    getTime() {
      return new Date().toString()
    }
  }
}
</script >

<style scoped>
@import url('https://use.fontawesome.com/releases/v5.8.1/css/all.css');

  .main{
    padding: 0 10px;
    margin: 10% auto 0 auto;
    text-align: center;
    width:250px;
    height: 400px;
    background:white;
    
  }
  h2{
    font-size: 4rem;
    font-weight:bold;
    font-family: serif;
    margin-bottom: 30px;
    padding: 30px;

  }
  .pa-3{
    width: 250px;
    color:#333;
    padding: 10px 18px;
    font-family: serif;
    font-size: 1.25rem;
    border-radius: 5px;
    background-color: rgb(219, 219, 219);
    box-shadow: 2px 2px 3px darkgray;
    transition-duration: 0.4s;
  }
  .pa-3:hover{
      background-color: rgb(182, 182, 182);
      color:white;
  }
  #main-signin{
    margin-top: 40px;
  }
  #main-signup{
    margin-top: 20px;
  }
</style>