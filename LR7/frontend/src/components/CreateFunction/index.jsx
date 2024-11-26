'use client'

import { fetchOperand } from '@/store/slices/operandSlice'
import instance from '@/utils/axiosInstance'
import { useRouter, useSearchParams } from 'next/navigation'
import { useState } from 'react'
import { useDispatch } from 'react-redux'
import styles from './index.module.css'
import MakeFrom from './MakeFrom'
import NewFuncArray from './NewFuncArray'
import NewFuncSimple from './NewFuncSimple'

export default () => {
	const dispatch = useDispatch()
	const router = useRouter()
	const params = useSearchParams()

	const [choice, setChoice] = useState(false)

	const num = parseInt(params.get('copy_to'))
	const copyto = num == num ? num : null

	async function onCreate() {
		if (copyto !== null) {
			await instance.post('/tabulated/operands/set', null, {
				params: { index: copyto },
			})
			await dispatch(fetchOperand(copyto)).unwrap()
			router.push('/operations')
		}
	}

	return (
		<div className={styles.wrapper}>
			<MakeFrom state={choice} setState={setChoice} />
			{choice ? <NewFuncSimple onCreate={onCreate} /> : <NewFuncArray onCreate={onCreate} />}
		</div>
	)
}
