import VerifiedInput from '../../VerifiedInput'
import styles from './index.module.css'

export default ({ count, setCount }) => {
	return (
		<div className={styles.wrapper}>
			<span className={styles.label}>Количество точек:</span>
			<VerifiedInput value={count} setValue={setCount} className={styles.input} />
		</div>
	)
}
