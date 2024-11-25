'use client'
import { useState } from 'react'
import NewFuncSimple from './NewFuncSimple'
import styles from './index.module.css'
import MakeFrom from './MakeFrom'
import NewFuncArray from './NewFuncArray'
import { useDispatch, useSelector } from 'react-redux'
import { fetchOperand, setCopyToOperator } from '@/store/slices/operandSlice'
import instance from '@/utils/axiosInstance'
import { useRouter } from 'next/navigation'

export default () => {
	const dispatch = useDispatch()
	const router = useRouter()

	const [choice, setChoice] = useState(false)
	const copyto = useSelector((state) => state.operands.copyto)

	async function onCreate(){
		if(copyto !== null){
			await instance.post("/tabulated/operands/set", null, {
				params: {index: copyto} 
			})
			await dispatch(fetchOperand(copyto)).unwrap()
			dispatch(setCopyToOperator(null))
			router.push("/operations")
		}
	}
	
	const createText = copyto !== null ? `Создать ${copyto+1} операнд` : "Создать"

	return (
		<div className={styles.wrapper}>
			<MakeFrom state={choice} setState={setChoice} />
			{choice ? <NewFuncSimple onCreate={onCreate} createText={createText}/> : <NewFuncArray onCreate={onCreate} createText={createText} />}
		</div>
	)
}
