<template>
  <div class="home">
    {{info}}
  </div>
</template>

<script>

export default {
  name: 'Home',
  data () {
    return {
      info: null,
      diaryNo: 2
    }
  },
  mounted () {
    axios
      .get('http://localhost:7000/magic/getDiary/' + this.diaryNo)
      .then(Response => {
        this.info = response.data
      })
      .catch(e => {
        conlsole.log(e)
        this.errored = true
      })
      .finally(() => this.loading = false)
  }
}
</script>
