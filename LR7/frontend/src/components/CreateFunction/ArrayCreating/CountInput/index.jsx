import NumberInput from '../../NumberInput'
import styles from './index.module.css'

export default () => {
	return (
		<div className={styles.wrapper}>
			<span className={styles.label}>Количество точек:</span>
			<NumberInput inputClass={styles.input} />
		</div>
	)
}
