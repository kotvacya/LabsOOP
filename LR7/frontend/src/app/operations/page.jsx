'use client'
import OperandFunction from '@/components/OperandFunction'
import ApplyButton from '@/components/OperandFunction/Buttons/ApplyButton'
import OperatorButton from '@/components/OperandFunction/Buttons/OperatorButton'
import classNames from '@/utils/classNames'
import { useSelector } from 'react-redux'
import styles from './page.module.css'

const unary = ['diff', 'int', 'add']

export default () => {
	const operatorConfig = useSelector((state) => state.operator)
	let cur = operatorConfig?.current
	const functions = useSelector((state) => state.operands.functions)

	const onApply = () => {
		// setFunctions
	}

	return (
		<div className={classNames(styles.wrapper, unary.includes(cur) && styles.short_wrapper)}>
			{!unary.includes(cur) && <OperandFunction id={0} points={functions[0]?.points || []} />}
			<OperatorButton className={classNames(styles.btn, unary.includes(cur) && styles.offset)} />
			<OperandFunction id={1} points={functions[1]?.points || []} />
			<ApplyButton className={styles.btn} onClick={onApply} />
			<OperandFunction id={2} points={functions[2]?.points || []} immutable />
		</div>
	)
}
