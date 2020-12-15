import HomeHeader from '@/components/common/HomeHeader'
import Footer from '@/components/common/Footer'
import DiaryList from '@/pages/diary/DiaryList'

export const DiaryListRouters = [{
    path: '/diary/diarylist',
    name: 'DiaryList',
    components: {
        header: HomeHeader,
        default: DiaryList,
        footer: Footer
    }
}]