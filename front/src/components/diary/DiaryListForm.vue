<template>
    <div class="DiaryForm">
    <table>
        <thead>
            <tr>
                <td width='25%'>날짜</td>
                <td width='15%'>감정상태</td>
                <td width='60%'>제목</td>
            </tr>
        </thead>
        <tbody>
            <tr :key="index" v-for="(value, index) in info">
                <td>{{value.diaryDate}}</td>
                <td>{{value.diaryLabels}}</td>
                <td>{{value.diaryAnswers}}</td>
            </tr>
        </tbody>
    </table>
    <button @click="write">글쓰기</button>
    </div>
</template>

<script>
export default {
    name: "DiaryForm",
    data() {
        return {
            info: [],
            memId:'test2',//수정
            loading: true,
            errored: false
            
        }
    },
    mounted() {
        axios
        .get("http://localhost:7000/magic/getAllDiary/"+this.memId)
        .then(response => {
            this.info = response.data;
        })
        .catch(e => {
            console.log(e);
            this.errored = true;
        })
        .finally(() => this.loading = false)
    }
}
</script>

<style scoped>

</style>