'use client'
import { setCopyToOperator } from '@/store/slices/operandSlice'
import { fetchCurrentFunction } from '@/store/slices/pointSlice'
import instance from '@/utils/axiosInstance'
import { useRouter } from 'next/navigation'
import { useEffect } from 'react'
import { useDispatch } from 'react-redux'
import ArrayFunction from '../ArrayFunction'
import CreateButton from './Buttons/CreateButton'
import SaveButton from './Buttons/SaveButton'
import UploadButton from './Buttons/UploadButton'
import styles from './index.module.css'

export default ({ id, immutable, ...rest }) => {
	const router = useRouter()
	const dispatch = useDispatch()

	async function onCreate(e) {
		await instance.post('/tabulated/operands/get', null, { params: { index: id } })
		dispatch(setCopyToOperator(id))
		await dispatch(fetchCurrentFunction()).unwrap()
		router.push('/')
	}
	const onSave = () => {}
	const onUpload = () => {}

	useEffect(() => {}, [])

	return (
		<div className={styles.wrapper}>
			<div className={styles.buttons}>
				{!immutable && <CreateButton onClick={onCreate} />}
				<SaveButton onClick={onSave} />
				{!immutable && <UploadButton onClick={onUpload} />}
			</div>
			<ArrayFunction {...rest} />
		</div>
	)
}
