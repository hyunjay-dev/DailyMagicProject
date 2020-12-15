import Vue from 'vue'
import Router from 'vue-router'
import { HomeRouters } from '@/router/home'
import { SigninRouters } from '@/router/auth_signin'
import { SignupRouters } from '@/router/auth_signup'
import { MemberRouters } from '@/router/member'
import { DiaryUploadRouters } from '@/router/diaryupload'
import { DiaryDetailRouters } from '@/router/diarydetail'
import { DiaryListRouters } from '@/router/diarylist'

Vue.use(Router)

export default new Router({
    mode: 'history',
    routes: [
        ...HomeRouters,
        ...SigninRouters,
        ...SignupRouters,
        ...MemberRouters,
        ...DiaryUploadRouters,
        ...DiaryDetailRouters,
        ...DiaryListRouters

    ]
})