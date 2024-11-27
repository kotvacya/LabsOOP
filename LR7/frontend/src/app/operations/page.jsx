'use client'
import OperandFunction from '@/components/OperandFunction'
import ApplyButton from '@/components/OperandFunction/Buttons/ApplyButton'
import OperatorButton from '@/components/OperandFunction/Buttons/OperatorButton'
import { setOperand } from '@/store/slices/operandSlice'
import instance from '@/utils/axiosInstance'
import classNames from '@/utils/classNames'
import { useDispatch, useSelector } from 'react-redux'
import styles from './page.module.css'

const unary = ['derivative', 'integral']

export default () => {
	const dispatch = useDispatch()
	const cur = useSelector((state) => state.operator)?.current

	async function onApply(e) {
		e.preventDefault()
		const response = await instance.post('/tabulated/operands/apply', null, {
			params: {
				operation: cur,
			},
		})

		dispatch(setOperand({ id: 2, data: response.data }))
	}

	return (
		<div className={classNames(styles.wrapper, unary.includes(cur) && styles.short_wrapper)}>
			{!unary.includes(cur) && <OperandFunction id={0} />}

			<OperatorButton className={classNames(styles.btn, unary.includes(cur) && styles.offset)} />

			<OperandFunction id={1} />

			<ApplyButton className={styles.btn} onClick={onApply} />

			<OperandFunction id={2} immutable />
		</div>
	)
}
