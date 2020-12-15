<template>
    <v-container>
        <h1>파일 업로더</h1>
        <input id="file-selector" ref="file" type="file" @change="handleFileUpload()" accept="image/*"> <!-- 0. type="file"은 파일업로드용, @change="" 발생시, handleFileUpload()를 실행시키겠다 (아래) -->
        <v-img v-if="imagePreview" max-height="150" max-width="150" :src="imagePreview"></v-img> <!-- 이미지 프리뷰가 False가 아니면, (값이 들어오면) image를 띄우겠다. -->
        <v-btn type="button" @click="onClickImageUpload()">이미지 업로드</v-btn>
        <div v-if="imgLabel">
            <span>당신의 감정상태는 {{imgLabel}}입니다. <!-- onClickImageUpload를 하면 가져오는 감정 값 -->
                오늘 당신의 하루는 {{diaryQuestions}}?
            </span>
            <v-text-field
                label="적어주세요"
                v-model="diaryAnswers"
            ></v-text-field>
            <span>오늘의 일기는?</span>
            <v-text-field
                label="적어주세요"
                v-model="diaryContents"
            ></v-text-field>
            <v-btn type="button" @click="diaryUpload()">저장</v-btn>
        </div>
    </v-container>    
</template>

<script>
export default {
    name: 'DiaryUploadForm',
    data(){
        return{
            memId:'test2', //로그인 정보로부터 받아올 것
            img:null,
            imagePath:null,
            imageName:null,
            imagePreview:null,
            imgExtension:null,
            imgLabel:null,
            diaryQuestions:null,
            diaryAnswers:null,
            diaryContents:null,
            questions:{
                'Angry':['화났다1','화났다2','화났다3'],
                'Disgust':['역겹다1','역겹다2','역겹다3'],
                'Fear':['무섭다1','무섭다2','무섭다3'],
                'Happy':['신난다1','신난다2','신난다3'],
                'Sad':['우울해1','우울해2','우울해3'],
                'Surprise':['놀랐다1','놀랐다2','놀랐다3'],
                'Neutral':['그냥 그래1','그냥 그래2','그냥 그래3']
            }
        }
    },
    methods:{
        handleFileUpload(){
            this.img = this.$refs.file.files[0]; // input 태그에서 선택한 파일을 의미하는데 이걸 this.img로 받음
            this.imagePreview = URL.createObjectURL(this.img); // 받은 이미지의 경로를 가져오는 방법으로 프리뷰 뜨게 하기위함
            let tmpExtension = this.img.name.split('.'); // 확장자를 유지하기 위해서 파일 네이밍에서 자르고(아랫줄)
            this.imgExtension = tmpExtension[tmpExtension.length-1]; // 맨 뒤의 확장자를 가져옴
        },
        onClickImageUpload(){ //이미지 업로드 + 감정분류
            let imgDate = new Date(); // 파일의 이름을 바꿔주기 위함으로 오늘의 날짜가 나옴
            let fd = new FormData(); // Axios로 보내기 위해서
            this.imageName = this.memId+"_"+imgDate.getFullYear()+"-"+(imgDate.getMonth()+1)+"-"+imgDate.getDate()+"."+this.imgExtension; // 아이디명+연도 날짜 등을 합치고+확장자를 합쳐줌
            fd.append('image', this.img); // form 데이터에 이미지와 이미지 이름을 같이 보냄
            fd.append('name', this.imageName);
            axios
                .post("http://localhost:7000/magic/imageUpload/", fd) 
                .then(resp =>{ 
                    this.imagePath = resp.data; // 스프링부트에서 패스를 받아옴
                    let fd2 = new FormData(); // 폼을 다시 생성해서 
                    fd2.append('path', this.imagePath); // 스프링부트에서 가져온 패스를 넣어줌
                    axios
                        .post("http://localhost:5000/predict", fd2) // 경로가 써있는 패스를 플라스크 포트로 보내줌 (플라스크의 predict) 
                        .then(resp =>{ // 플라스크에서 값을 가져옴
                            this.imgLabel = resp.data.label; // 플라스크의 Predict 함수의 결과물 중 Label 값만 가져오겠다.
                            this.diaryQuestions = this.questions[this.imgLabel][1] //위에 퀘스쳔에서 값이 [1]인 애들을 출력하게 해서 this.diaryQuestions으로 출력(기준을 만들어야 함)
                        })
                })
        },
        diaryUpload(){
            const diaryObj = {
                memId : this.memId,
                diaryQuestions : this.diaryQuestions,
                diaryAnswers : this.diaryAnswers,
                diaryContents : this.diaryContents,
                diaryLabels : this.imgLabel,
                diaryImage : this.imageName,
                diaryEmotions : 'https://w7.pngwing.com/pngs/288/793/png-transparent-smiling-emoticon-emoji-smiley-face-eye-crying-emoji-face-head-smiley.png'
            }
            axios
                .post("http://localhost:7000/magic/addDiary/", diaryObj)
                .then(resp =>{
                    alert("일기쓰기 성공");
                })
        }
    }
    
}
</script>

<style scoped>

</style>