import NumberInput from '../../NumberInput'
import styles from './index.module.css'

export default ({count, setCount}) => {
	return (
		<div className={styles.wrapper}>
			<span className={styles.label}>Количество точек:</span>
			<NumberInput value={count} setValue={setCount} className={styles.input} />
		</div>
	)
}
