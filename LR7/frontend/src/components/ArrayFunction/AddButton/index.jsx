import classNames from '@/utils/classNames'
import styles from './index.module.css'

export default ({ onClick, className, ...rest }) => {
	return (
		<button className={classNames(className, styles.btn)} onClick={onClick} {...rest}>
			<svg
				width='32px'
				height='32px'
				viewBox='0 0 24 24'
				fill='#1e1e1e'
				xmlns='http://www.w3.org/2000/svg'
			>
				<path
					d='M6 12H18M12 6V18'
					stroke='#1e1e1e'
					strokeWidth='2'
					strokeLinecap='round'
					strokeLinejoin='round'
				/>
			</svg>
		</button>
	)
}
