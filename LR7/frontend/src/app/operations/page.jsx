'use client'
import OperandFunction from '@/components/OperandFunction'
import ApplyButton from '@/components/OperandFunction/Buttons/ApplyButton'
import OperatorButton from '@/components/OperandFunction/Buttons/OperatorButton'
import { useState } from 'react'
import styles from './page.module.css'

export default () => {
	const [operation, setOperation] = useState()
	const points = [{ id: 1, x: 1, y: 1 }]
	return (
		<div className={styles.wrapper}>
			<OperandFunction points={points} id={0} />
			<OperatorButton state={operation} setState={setOperation} />
			<OperandFunction points={points} id={1} disabled={operation == 'd'} />
			<ApplyButton />
			<OperandFunction points={points} id={2} immutable />
		</div>
	)
}
