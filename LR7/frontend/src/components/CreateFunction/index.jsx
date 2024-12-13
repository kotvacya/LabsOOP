'use client'
import { useState } from 'react'
import styles from './index.module.css'
import MakeFrom from './MakeFrom'
import NewFuncArray from './NewFuncArray'
import NewFuncComposite from './NewFuncComposite'
import NewFuncSimple from './NewFuncSimple'
import { useRouter, useSearchParams } from 'next/navigation'
import instance from '@/utils/axiosInstance'
import { useDispatch } from 'react-redux'
import { fetchOperand } from '@/store/slices/operandSlice'
import { Suspense } from 'react'

function CreateFunction(){
	const [choice, setChoice] = useState(0)
	const dispatch = useDispatch()
	const router = useRouter()

	const params = useSearchParams()
	const num = parseInt(params.get('copy_to'))
	const copy_to = isNaN(num) ? null : num

	const createText = copy_to !== null && `Создать операнд ${copy_to + 1}`

	const doCopy = copy_to !== null ? async () => {
		await instance.post('/tabulated/operands/set', null, { params: { index: copy_to } })
		await dispatch(fetchOperand(copy_to)).unwrap()
		router.push(params.get('return_to'))
	} : async () => {}

	return (
		<div className={styles.wrapper}>
			<MakeFrom state={choice} setState={setChoice} />
			{choice == 0 && <NewFuncArray doCopy={doCopy} createText={createText}/>}
			{choice == 1 && <NewFuncSimple doCopy={doCopy} createText={createText}/>}
			{choice == 2 && <NewFuncComposite />}
		</div>
	)
}

export default () => {
	return (<Suspense>
		<CreateFunction/>
	</Suspense>)
}
