import HomeHeader from '@/components/common/HomeHeader'
import Footer from '@/components/common/Footer'
import DiaryUpload from '@/pages/diary/DiaryUpload'

export const DiaryUploadRouters = [{
    path: '/diary/diaryupload',
    name: 'DiaryUpload',
    components: {
        header: HomeHeader,
        default: DiaryUpload,
        footer: Footer

    }
}]