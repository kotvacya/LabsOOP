import OperandFunction from '@/components/OperandFunction'
import Chart from '../../components/Chart'
import styles from './page.module.css'

export default () => {
	const len = 100
	const x = Array.from({ length: len }, (el, i) => i)
	const y = Array.from({ length: len }, () => Math.random() * 1000)
	const points = []
	for (let i = 0; i < len; ++i) points.push({ id: i, x: x[i], y: y[i] })

	return (
		<div className={styles.wrapper}>
			<OperandFunction points={points}></OperandFunction>
			<div className={styles.chart}>
				<Chart xData={x} yData={y} />
			</div>
		</div>
	)
}
