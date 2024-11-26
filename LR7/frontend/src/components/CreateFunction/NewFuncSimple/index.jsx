'use client'
import Dropdown from '@/components/Dropdown'
import VerifiedInput from '@/components/VerifiedInput'
import { setCount, setEnd, setFunction, setStart } from '@/store/slices/functionConfigSlice'
import instance from '@/utils/axiosInstance'
import { countVerifier, floatVerifier } from '@/utils/verifiers'
import { useDispatch, useSelector } from 'react-redux'
import CreateButton from '../CreateButton'
import styles from './index.module.css'

export default ({ onCreate }) => {
	const functionConfig = useSelector((state) => state.simpleFunctionConfig)
	const dispatch = useDispatch()

	const onCreateClick = async () => {
		const response = await instance.post('/tabulated/current/simple', functionConfig.config)
		console.log(response)
		await onCreate()
	}

	return (
		<fieldset className={styles.form}>
			<legend className={styles.legend}>ArrayTabulatedFunction</legend>
			<div className={styles.container}>
				<p className={styles.property}>Простая функция</p>
				<Dropdown
					className={styles.dropdown}
					name='Выберите фунцкию'
					value={functionConfig.config.function}
					setValue={(val) => dispatch(setFunction(val))}
					content={functionConfig.functions}
				/>
				<p className={styles.property}>Начало области</p>
				<VerifiedInput
					value={functionConfig.config.start}
					setValue={(val) => dispatch(setStart(val))}
					getError={(text, firstTime) =>
						floatVerifier(text, firstTime, (val) => dispatch(setStart(val)))
					}
				/>
				<p className={styles.property}>Конец области</p>
				<VerifiedInput
					value={functionConfig.config.end}
					setValue={(val) => dispatch(setEnd(val))}
					getError={(text, firstTime) =>
						floatVerifier(text, firstTime, (val) => dispatch(setEnd(val)))
					}
				/>
				<p className={styles.property}>Количество точек</p>
				<VerifiedInput
					value={functionConfig.config.count}
					setValue={(val) => dispatch(setCount(val))}
					getError={(text, firstTime) =>
						countVerifier(text, firstTime, (val) => dispatch(setCount(val)))
					}
				/>
			</div>
			<CreateButton onClick={onCreateClick} />
		</fieldset>
	)
}
