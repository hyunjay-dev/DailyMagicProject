import Home from '@/pages/Home'

import MainHeader from '@/components/common/MainHeader'
import MenuBar from '@/components/common/MenuBar'
import Footer from '@/components/common/Footer'

export const HomeRouters = [{
    path: '/',
    name: 'Home',
    components: {
        header: MainHeader,
        menu: MenuBar,
        default: Home,
        footer: Footer
    }
}]