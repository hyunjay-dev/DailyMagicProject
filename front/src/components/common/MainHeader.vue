<template>
    <div align="right">
        <router-link :to="{name:'Signin'}" class="pa-3">로그인</router-link>
        <router-link :to="{name:'Signup'}" class="pa-3">회원가입</router-link>
    </div>
</template>

<script>
export default {
    name: 'MainHeader'
}
</script>

<style scoped>
    .pa-3 {
        padding: 1%;
        color: blue;
    }
</style>