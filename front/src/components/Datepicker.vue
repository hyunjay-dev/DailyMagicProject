<template>
  <v-row justify="center">
    <v-date-picker
      v-model="date"
      :allowed-dates="allowedDates"
      class="mt-4"
      min="1900-01-01"
      max="2100-12-31"
    ></v-date-picker>
  </v-row>
</template>

<script>
  export default {
    data: () => ({
      date: '2020-12-11',
    }),

    methods: {
      allowedDates: val => parseInt(val.split('-')[2], 10) % 2 === 0,
    },
  }
</script>