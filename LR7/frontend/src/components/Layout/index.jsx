import Header from './Header'
import styles from './index.module.css'

export default ({ children }) => {
	return (
		<div className={styles.wrapper}>
			<Header />
			<div className={styles.container}>{children}</div>
		</div>
	)
}
