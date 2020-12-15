<template>
  <form @submit.prevent="onSubmit">
    <div data-app>
        <v-flex xs12 sm6 md4>
        <v-text-field
        label="아이디"
        v-model="memId"
        @keyup="idCheck"
        required
        ></v-text-field>
        <span v-if="memIdCheck==1">사용할 수 없는 아이디입니다.</span>

        <v-text-field
        label="비밀번호"
        type="password"
        v-model="memPw"
        required
        ></v-text-field>

        <v-text-field
        label="비밀번호 확인"
        type="password"
        v-model="memPw2"
        @keyup="pwCheck"
        required
        ></v-text-field>
        <span v-if="!memPwCheck"> 위 비밀번호와 일치하지 않습니다.</span>

        <v-text-field
        label="닉네임"
        v-model="memName"
        required
        ></v-text-field>

        <v-select
        label="기념일"
        v-model="memAnniversary"
        :items="Anniversary"
        :rules="rules.Anniversary"
        color="pink"
        required
        >
        </v-select>

        
        <v-menu
        ref="menu"
        v-model="menu"
        :close-on-content-click="false"
        :nudge-right="40"
        transition="scale-transition"
        offset-y
        min-width="290px"
        >
        <template v-slot:activator="{ on, attrs }">
            <v-text-field
            v-model="memDate"
            label="Anniversary date"
            prepend-icon="mdi-calendar"
            readonly
            v-bind="attrs"
            v-on="on"
            ></v-text-field>
        </template>
        <v-date-picker
        ref="picker"
        v-model="memDate"
        color="green lighten-1"
        valueType="format" :format="'YYYY-MM-DD'"
        value="Stirng"
        @change="save"
        ></v-date-picker>
        </v-menu>
        </v-flex>
    </div>
    <div>
      <button type="submit" name="button" :disabled="memIdCheck||!memPwCheck" class='button'>회원가입</button>
    </div>
  </form>
</template>

<script>
export default {
  name: 'SignupForm',
  data: () => ({
      memDate: null,
      memId: null,
      memName: null,
      memPw: null,
      memPw2: null,
      memAnniversary: null,
      memIdCheck: 0,
      memPwCheck: true,
      date: new Date().toISOString().substr(0, 10).toString(),
      selected:'',
      rules:{
          Anniversary:[val => (val || '').length > 0 || 'This field is required']
      },
      Anniversary:['생일', '결혼기념일', '회원가입일']
    }),
  watch:{
      menu (val) {
          val && setTimeout(() => (this.$refs.picker.activePicker = 'YEAR'))
      }
  },
  methods: {
    save(date){
        this.$refs.menu.save(date)
    },
    onSubmit () {
      const { memId, memName, memPw, memAnniversary, memDate } = this
      this.$emit('submit', { memId, memName, memPw, memAnniversary, memDate })
    },
    idCheck() {
        axios
            .get("http://localhost:7000/magic/idCheck/" + this.memId)
            .then(response => (this.memIdCheck = response.data))
            .catch(error => {
                console.log(error);
                this.errored = true
            })
            .finally(() => this.empEmpty = false)
    },
    pwCheck() {
        if(this.memPw!==this.memPw2){
            this.memPwCheck = false;
        }else{
            this.memPwCheck = true;
        }
    }
  }  
}
</script>

<style>
 
</style>