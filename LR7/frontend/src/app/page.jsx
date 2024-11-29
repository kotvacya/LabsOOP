'use client'
import OperandFunction from '@/components/OperandFunction'
import ApplyButton from '@/components/OperandFunction/Buttons/ApplyButton'
import OperatorButton from '@/components/OperandFunction/Buttons/OperatorButton'
import { setOperand } from '@/store/slices/operandSlice'
import instance from '@/utils/axiosInstance'
import classNames from '@/utils/classNames'
import { useState } from 'react'
import { useDispatch, useSelector } from 'react-redux'
import styles from './page.module.css'
import { createTimeLimitedPopup } from '@/store/slices/confirmationModalSlice'

const unary = ['derivative', 'integral']

export default () => {
	const dispatch = useDispatch()
	const [text, setText] = useState()
	const cur = useSelector((state) => state.operator)?.current

	async function onApply(e) {
		e.preventDefault()
		
		try{
			let response;
			if(cur === "integral"){
				response = await instance.get('/tabulated/operands/1/applyIntegral')
			}else{
				response = await instance.post('/tabulated/operands/apply', null, {
					params: {
						operation: cur,
					},
				})
			}

			if (cur == 'integral') setText(response.data)
			else dispatch(setOperand({ id: 2, data: response.data }))
		}catch(error){
			dispatch(createTimeLimitedPopup({ success: false, message: error.response?.data?.error || error, duration: 5 }))
		}
	}

	return (
		<div className={classNames(styles.wrapper, unary.includes(cur) && styles.short_wrapper)}>
			{!unary.includes(cur) && <OperandFunction id={0} />}

			<OperatorButton className={classNames(styles.btn, unary.includes(cur) && styles.offset)} />

			<OperandFunction id={1} />

			<ApplyButton className={styles.btn} onClick={onApply} />

			{cur == 'integral' ? (
				<input className={styles.input} value={text} placeholder='поле ответа' readOnly />
			) : (
				<OperandFunction id={2} immutable />
			)}
		</div>
	)
}
