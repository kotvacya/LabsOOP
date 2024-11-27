import classNames from '@/utils/classNames'
import styles from './index.module.css'

export default ({ className, ...rest }) => {
	return (
		<button {...rest} className={classNames(styles.button, className)}>
			=
		</button>
	)
}
