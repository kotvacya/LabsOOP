'use client'
import classNames from '@/utils/classNames'
import styles from './index.module.css'

export default ({ text, ...rest }) => {
	return (
		<button
			className={classNames(styles.btn)}
			{...rest}
			>
			{text}
		</button>
	)
}