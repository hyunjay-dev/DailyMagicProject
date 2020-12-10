<template>
<<<<<<< HEAD
  <div class="home">
=======
  <div>
>>>>>>> c1a5af84323e41cbcfa4c03d45a7ef721945718b
    {{info}}
  </div>
</template>

<script>

export default {
  name: 'Home',
  data () {
    return {
<<<<<<< HEAD
      info: null,
      diaryNo: 2
=======
      diaryNum: '2',
      info: null
>>>>>>> c1a5af84323e41cbcfa4c03d45a7ef721945718b
    }
  },
  mounted () {
    axios
<<<<<<< HEAD
      .get('http://localhost:7000/magic/getDiary/' + this.diaryNo)
      .then(Response => {
        this.info = response.data
      })
      .catch(e => {
        conlsole.log(e)
=======
      .get('http://localhost:7000/magic/getDiary/' + this.diaryNum)
      .then(response => {
        console.log('a')
        this.info = response.data
      })
      .catch(e => {
        console.log(e)
>>>>>>> c1a5af84323e41cbcfa4c03d45a7ef721945718b
        this.errored = true
      })
      .finally(() => this.loading = false)
  }
}
</script>
