'use client'
import OperandFunction from '@/components/OperandFunction'
import ApplyButton from '@/components/OperandFunction/Buttons/ApplyButton'
import OperatorButton from '@/components/OperandFunction/Buttons/OperatorButton'
import styles from './page.module.css'
import { useSelector } from 'react-redux'

export default () => {
	const functions = useSelector((state => state.operands.functions))
	
	return (
		<div className={styles.wrapper}>
			<OperandFunction points={functions[0]?.points || []} id={0} />
			<OperatorButton/>
			<OperandFunction points={functions[1]?.points || []} id={1} />
			<ApplyButton />
			<OperandFunction points={functions[2]?.points || []} id={2} immutable />
		</div>
	)
}
