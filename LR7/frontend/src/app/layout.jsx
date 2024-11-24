import Layout from '@/components/Layout'
import Provider from '@/store/Provider'
import localFont from 'next/font/local'
import './globals.css'
import Preloader from '@/components/Preloader'

export const metadata = {
	title: 'LR7',
	description: 'UI for tabulated functions',
}

export default function RootLayout({ children }) {
	return (
		<html lang='ru'>
			<body className={comfortaa.className}>
				<Provider>
					<Preloader/>
					<Layout>{children}</Layout>
				</Provider>
			</body>
		</html>
	)
}

const comfortaa = localFont({
	src: [
		{
			path: '../../public/Fonts/Comfortaa-Light.ttf',
			weight: '300',
			style: 'light',
		},
		{
			path: '../../public/Fonts/Comfortaa-Regular.ttf',
			weight: '400',
			style: 'regular',
		},
		{
			path: '../../public/Fonts/Comfortaa-Medium.ttf',
			weight: '500',
			style: 'medium',
		},
		{
			path: '../../public/Fonts/Comfortaa-SemiBold.ttf',
			weight: '600',
			style: 'semi-bold',
		},
		{
			path: '../../public/Fonts/Comfortaa-Bold.ttf',
			weight: '700',
			style: 'bold',
		},
	],
	variable: '--comfortaa',
})
