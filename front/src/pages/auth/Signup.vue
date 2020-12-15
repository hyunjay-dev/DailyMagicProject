<template>
    <div align="center">
        <h2>회원가입</h2>
    <signup-form @submit="onSubmit"></signup-form>
    </div>
</template>

<script>
import api from '@/api'
import SignupForm from '@/components/auth/SignupForm'


export default {
    name:'Signup',
    components:{SignupForm},
    methods: {
        onSubmit (payload) {
        const { memId, memName, memPw, memAnniversary, memDate } = payload
        api.post('http://localhost:7000/magic/addMember', payload)
            .then(res => {
            alert('등록이 완료되었습니다.')
            this.$router.push({
            name: 'Home'
                })
            })
            .catch(err => {
            alert(err.response.data)
            })
        }
    }
}
</script>