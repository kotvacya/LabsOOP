'use client'
import { useState } from 'react'
import NewFuncSimple from './NewFuncSimple'
import styles from './index.module.css'
import MakeFrom from './MakeFrom'
import NewFuncArray from './NewFuncArray'
import { useDispatch } from 'react-redux'
import { fetchOperand } from '@/store/slices/operandSlice'
import instance from '@/utils/axiosInstance'
import { useRouter, useSearchParams } from 'next/navigation'

export default () => {
	const dispatch = useDispatch()
	const router = useRouter()
	const params = useSearchParams()

	const [choice, setChoice] = useState(false)
	
	const num = parseInt(params.get("copy_to"))
	const copyto = num == num ? num : null

	async function onCreate(){
		if(copyto !== null){
			await instance.post("/tabulated/operands/set", null, {
				params: {index: copyto} 
			})
			await dispatch(fetchOperand(copyto)).unwrap()
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
