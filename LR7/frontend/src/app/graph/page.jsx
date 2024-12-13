'use client'
import ExploreGraph from '@/components/ExploreGraph'
import OperandFunction from '@/components/OperandFunction'
import { useSelector } from 'react-redux'
//import Chart from '../../components/Chart'
import styles from './page.module.css'

import dynamic from 'next/dynamic'

const Chart = dynamic(() => import('../../components/Chart'), {
	ssr: false
})

export default () => {
	const functions = useSelector((state) => state.operands.functions)

	const points = functions[3]?.points || []
	const insertable = functions[3]?.insertable || false

	return (
		<div className={styles.wrapper}>
			<div className={styles.operand}>
				<OperandFunction id={3} points={points} />
			</div>

			<div className={styles.apply}>
				<ExploreGraph insertable={insertable} />
			</div>

			<div className={styles.chart}>
				{points.length != 0 && (
					<Chart
						className={styles.graph}
						xData={points.map((el) => el.x)}
						yData={points.map((el) => el.y)}
					/>
				)}
				{points.length == 0 && 'Здесь будет график...'}
			</div>
		</div>
	)
}
