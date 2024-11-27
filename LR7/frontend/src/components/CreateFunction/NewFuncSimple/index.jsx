'use client'
import Dropdown from '@/components/Dropdown'
import VerifiedInput from '@/components/VerifiedInput'
import { setCount, setEnd, setFunction, setStart } from '@/store/slices/SimpleFunctionSlice'
import instance from '@/utils/axiosInstance'
import { countVerifier, floatVerifier } from '@/utils/verifiers'
import { useDispatch, useSelector } from 'react-redux'
import CreateButton from '../CreateButton'
import styles from './index.module.css'
import { createTimeLimitedPopup } from '@/store/slices/confirmationModalSlice'

export default function NewFuncSimple({doCopy, createText}) {
	const functionConfig = useSelector((state) => state.simpleFunctionConfig)
	const dispatch = useDispatch()

	const isCorrect =
		floatVerifier(functionConfig.config.start) &&
		floatVerifier(functionConfig.config.end) &&
		countVerifier(functionConfig.config.count) &&
		functionConfig.config.function

	const createSimpleFunc = async () => {
		const response = await instance.post('/tabulated/current/simple', functionConfig.config)
		if(doCopy) await doCopy()
		dispatch(createTimeLimitedPopup({ success: true, message: "Функция успешно создана", duration: 5 }))
	}

	return (
		<fieldset className={styles.form}>
			<legend className={styles.legend}>ArrayTabulatedFunction</legend>
			<div className={styles.container}>
				<p className={styles.property}>Простая функция</p>
				<Dropdown
					className={styles.dropdown}
					name='Выберите функцию'
					value={functionConfig.config.function}
					setValue={(val) => dispatch(setFunction(val))}
					content={functionConfig.functions}
				/>
				<p className={styles.property}>Начало области</p>
				<VerifiedInput
					value={functionConfig.config.start}
					setValue={(val) => dispatch(setStart(val))}
					checkCorrect={floatVerifier}
				/>
				<p className={styles.property}>Конец области</p>
				<VerifiedInput
					value={functionConfig.config.end}
					setValue={(val) => dispatch(setEnd(val))}
					checkCorrect={floatVerifier}
				/>
				<p className={styles.property}>Количество точек</p>
				<VerifiedInput
					value={functionConfig.config.count}
					setValue={(val) => dispatch(setCount(val))}
					checkCorrect={countVerifier}
				/>
			</div>
			<CreateButton onClick={createSimpleFunc} text={createText || "Создать"} disabled={!isCorrect} />
		</fieldset>
	)
}
