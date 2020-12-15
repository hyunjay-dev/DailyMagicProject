<template>
    <div>
    <table border="1">
            <tr>
                <td>날짜</td>
                <td>{{diary.diaryDate}}</td>
            </tr>
            <tr>
                <td>사진</td>
                <td><img :src="'http://localhost:7000/image/'+diary.diaryImage"></td>
            </tr>
            <tr>
                <td>이 날의 질문</td>
                <td>{{diary.diaryQuestions}}</td>
            </tr>
            <tr>
                <td>이 날의 답변</td>
                <td>{{diary.diaryAnswers}}</td>
            </tr>
            <tr>
                <td>이 날의 일기</td>
                <td>{{diary.diaryContents}}</td>
            </tr>
        </table>
    </div>
</template>

<script>
export default {
    name: 'DiaryDetailForm',
    data(){
        return{
            diary: null,
            loading: true,
            errored: false
        }
    },
    mounted(){
        const diaryId = 15
        axios
            .get("http://localhost:7000/magic/getDiary/"+diaryId)
            .then(resp =>{
                this.diary = resp.data;
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