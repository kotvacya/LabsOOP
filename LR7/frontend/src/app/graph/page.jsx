import Chart from '../../components/Chart'
import styles from './page.module.css'

export default () => {
	const len = 30
	const x = Array.from({ length: len }, (el, i) => i)
	const y = Array.from({ length: len }, () => Math.random() * 10)

	return (
		<div className={styles.wrapper}>
			<Chart xData={x} yData={y} />
		</div>
	)
}
