<template>
    <form @submit.prevent="OnSubmit">
        <div id='signinform'>
            <v-flex xs12 sm6 md4>
                <v-text-field
                label="ID"
                v-model="memId"
                required>
                </v-text-field>
                <v-text-field
                label="Password"
                type="password"
                v-model="memPw"
                required>
                </v-text-field>
            </v-flex>
        </div>
        <div>
            <button type="submit" 
            name="button" 
            :disabled="memIdCheck||!memPwCheck" 
            v-on:click="OnSubmit">
            로그인
            </button>
        </div>
    </form>
</template>

<script>
export default {
    name: 'SigninForm',
    data: () => ({
            memId: null,
            memPw: null,
            memIdCheck: 0,
            memPwCheck: true,
            IdCheck: true
        }),
    methods:{
        OnSubmit() {
            const {memId, memPw} = this
            this.$emit('submit', {memId, memPw})
            
            },
        
    }
}
</script>
