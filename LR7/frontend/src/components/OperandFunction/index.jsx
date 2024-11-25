'use client'
import classNames from '@/utils/classNames'
import ArrayFunction from '../ArrayFunction'
import CreateButton from './Buttons/CreateButton'
import SaveButton from './Buttons/SaveButton'
import UploadButton from './Buttons/UploadButton'
import styles from './index.module.css'
import { useEffect } from 'react'
import instance from '@/utils/axiosInstance'
import { useRouter } from 'next/navigation'
import { useDispatch } from 'react-redux'
import { fetchCurrentFunction } from '@/store/slices/pointSlice'
import { setCopyToOperator } from '@/store/slices/operandSlice'

export default ({ id, immutable, disabled, ...rest }) => {

	const router = useRouter()
	const dispatch = useDispatch();

	async function onCreate(e) {
		await instance.post("/tabulated/operands/get", null, {
			params: {index: id} 
		})
		dispatch(setCopyToOperator(id))
		await dispatch(fetchCurrentFunction()).unwrap()
		router.push("/")

	}
	const onSave = () => {}
	const onUpload = () => {}

	useEffect(() => {

	}, [])

	return (
		<div className={classNames(styles.wrapper, disabled && styles.disabled)}>
			<div className={styles.buttons}>
				{!immutable && <CreateButton onClick={onCreate} />}
				<SaveButton onClick={onSave} />
				{!immutable && <UploadButton onClick={onUpload} />}
			</div>
			<ArrayFunction {...rest} />
		</div>
	)
}
