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
			path: '../../public/fonts/Comfortaa-Light.ttf',
			weight: '300',
			style: 'light',
		},
		{
			path: '../../public/fonts/Comfortaa-Regular.ttf',
			weight: '400',
			style: 'regular',
		},
		{
			path: '../../public/fonts/Comfortaa-Medium.ttf',
			weight: '500',
			style: 'medium',
		},
		{
			path: '../../public/fonts/Comfortaa-SemiBold.ttf',
			weight: '600',
			style: 'semi-bold',
		},
		{
			path: '../../public/fonts/Comfortaa-Bold.ttf',
			weight: '700',
			style: 'bold',
		},
	],
	variable: '--comfortaa',
})
