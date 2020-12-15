<template>
    <v-container>
        <h1>파일 업로더</h1>
        <input id="file-selector" ref="file" type="file" @change="handleFileUpload()" accept="image/*">
        <v-img v-if="imagePreview" max-height="150" max-width="150" :src="imagePreview"></v-img>
        <v-btn type="button" @click="onClickImageUpload()">이미지 업로드</v-btn>
        <div v-if="imgLabel">
            <span>당신의 감정상태는 {{imgLabel}}입니다.
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
import DiaryUpload from '@/components/diary/DiaryUploadForm'

export default {
    name: 'DiaryUpload',
    components: {DiaryUpload},
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
            this.img = this.$refs.file.files[0];
            this.imagePreview = URL.createObjectURL(this.img);
            let tmpExtension = this.img.name.split('.');
            this.imgExtension = tmpExtension[tmpExtension.length-1];
        },
        onClickImageUpload(){ //이미지 업로드 + 감정분류
            let imgDate = new Date();
            let fd = new FormData();
            this.imageName = this.memId+"_"+imgDate.getFullYear()+"-"+(imgDate.getMonth()+1)+"-"+imgDate.getDate()+"."+this.imgExtension;
            fd.append('image', this.img);
            fd.append('name', this.imageName);
            axios
                .post("http://localhost:7000/magic/imageUpload/", fd)
                .then(resp =>{
                    this.imagePath = resp.data;
                    let fd2 = new FormData();
                    fd2.append('path', this.imagePath);
                    axios
                        .post("http://localhost:5000/predict", fd2)
                        .then(resp =>{
                            this.imgLabel = resp.data.label;
                            this.diaryQuestions = this.questions[this.imgLabel][1] //랜덤선택으로 바꿀것
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