<template>
    <div align="center">
        <h2>로그인</h2>
    <signin-form @submit="onSubmit"></signin-form>
    </div>
</template>

<script>
import SigninForm from '@/components/auth/SigninForm'
import api from '@/api'

export default {
    name:'Signin',
    components:{SigninForm},
    methods:{
        data:() =>({
        isLogin: false
        }),
        mounted(){
            if(localStorage.getItem("mem_login") != null){
                this.isLogin = true;
            } 
        },
        onSubmit (name) {
            const {memId, memPw} = name
            api.post('http://localhost:7000/magic/login' , name)
                .then(response =>{
                    console.log(response.data);
                    if (response.data==""){
                        this.IdCheck = false
                    }else {
                        this.info = response.data
                        localStorage.setItem(this.info.memId, this.info.memPw)
                    }
                    alert(response.data.memName + "님이 로그인하셨습니다.")
                    this.$router.push({
                        name: 'Home'
                    })
                })
                .catch(err=>{
                    alert(err.response.data)
                })
        }
    }
    
}
</script>

