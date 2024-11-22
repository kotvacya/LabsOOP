'use client'
import classNames from '@/utils/classNames'
import Link from 'next/link'
import { usePathname } from 'next/navigation'
import styles from './index.module.css'

export default ({ href, children }) => {
	const path = usePathname()

	return (
		<Link className={classNames(styles.link, path == href && styles.active)} href={href}>
			{children}
		</Link>
	)
}
