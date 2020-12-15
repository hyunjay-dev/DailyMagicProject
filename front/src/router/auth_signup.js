import HomeHeader from '@/components/common/HomeHeader'
import Footer from '@/components/common/Footer'
import Signup from '@/pages/auth/Signup'

export const SignupRouters = [{
    path: '/auth/signup',
    name: 'Signup',
    components: {
        header: HomeHeader,
        default: Signup,
        footer: Footer
    }
}]