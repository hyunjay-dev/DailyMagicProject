import HomeHeader from '@/components/common/HomeHeader'
import Footer from '@/components/common/Footer'
import DiaryDetail from '@/pages/diary/DiaryDetail'

export const DiaryDetailRouters = [{
    path: '/diary/diarydetail',
    name: 'DiaryDetail',
    components: {
        header: HomeHeader,
        default: DiaryDetail,
        footer: Footer
    }
}]