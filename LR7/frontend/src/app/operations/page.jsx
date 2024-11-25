'use client'
import OperandFunction from '@/components/OperandFunction'
import ApplyButton from '@/components/OperandFunction/Buttons/ApplyButton'
import OperatorButton from '@/components/OperandFunction/Buttons/OperatorButton'
import styles from './page.module.css'
import { useDispatch, useSelector } from 'react-redux'
import instance from '@/utils/axiosInstance'
import { setOperand } from '@/store/slices/operandSlice'

export default () => {
	const functions = useSelector((state => state.operands.functions))
	const operatorConfig = useSelector(state => state.operator)
	const dispatch = useDispatch();

	async function onApply(e) {
		const response = await instance.post("/tabulated/operands/apply", null, {
			params: {
				operation: operatorConfig.current
			}
		})
		
		dispatch(setOperand({id: 2, data: response.data}))
	}
	
	return (
		<div className={styles.wrapper}>
			<OperandFunction points={functions[0]?.points || []} id={0} />
			<OperatorButton/>
			<OperandFunction points={functions[1]?.points || []} id={1} />
			<ApplyButton onClick={onApply} />
			<OperandFunction points={functions[2]?.points || []} id={2} immutable />
		</div>
	)
}
