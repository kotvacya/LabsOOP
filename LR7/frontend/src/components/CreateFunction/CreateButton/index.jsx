'use client'
import { createTimeLimitedPopup } from '@/store/slices/confirmationModalSlice'
import { fetchOperand } from '@/store/slices/operandSlice'
import instance from '@/utils/axiosInstance'
import classNames from '@/utils/classNames'
import { useRouter, useSearchParams } from 'next/navigation'
import { useDispatch } from 'react-redux'
import styles from './index.module.css'

export default ({ create, disabled, type = 0 }) => {
	const dispatch = useDispatch()
	const router = useRouter()
	const params = useSearchParams()
	const num = parseInt(params.get('copy_to'))

	const createOperand = async (e) => {
		e.preventDefault()
		await create()
		await instance.post('/tabulated/operands/set', null, { params: { index: num } })
		await dispatch(fetchOperand(num)).unwrap()
		router.push('/operations')
	}

	const createComposite = () => {
		dispatch(createTimeLimitedPopup({ success: true, message: createMessage, duration: 5 }))
	}

	const saveFunction = () => {
		dispatch(createTimeLimitedPopup({ success: true, message: saveMessage, duration: 5 }))
	}

	return (
		<button
			className={classNames(styles.btn, disabled && styles.btn_disabled)}
			onClick={isNaN(num) ? (type ? createComposite : saveFunction) : createOperand}
		>
			{isNaN(num) ? (type ? 'Создать' : 'Сохранить') : `Создать ${num + 1} операнд`}
		</button>
	)
}

const createMessage = 'Функция успешно создана'
const saveMessage = 'Функция успешно сохранена'
