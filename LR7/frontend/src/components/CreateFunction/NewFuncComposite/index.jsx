'use client'
import Dropdown from '@/components/Dropdown'
import VerifiedInput from '@/components/VerifiedInput'
import { useDispatch, useSelector } from 'react-redux'
import CreateButton from '../CreateButton'
import styles from './index.module.css'
import { fetchSimpleFunctions } from '@/store/slices/SimpleFunctionSlice'
import { setInner, setName, setOuter } from '@/store/slices/CompositeFunctionSlice'
import instance from '@/utils/axiosInstance'

export default () => {
	const dispatch = useDispatch()
	const functions = useSelector((state) => state.simpleFunctionConfig.functions)
	const functionConfig = useSelector((state) => state.compositeFunctionConfig)

	const isCorrect = !!functionConfig.config.name
	const createCompositeFunc = async () => {
		await instance.post('/tabulated/simple/composite', functionConfig.config)
		await dispatch(fetchSimpleFunctions()).unwrap()
	}

	return (
		<div className={styles.wrapper}>
			<div className={styles.column_wrapper}>
				<h2 className={styles.title}>F(x)</h2>
				<Dropdown // TODO
					className={styles.dropdown}
					name='Выберите f(x)'
					value={functionConfig.config.inner}
					setValue={(val) => dispatch(setInner(val))}
					content={functions}
				/>
			</div>

			<div className={styles.column_wrapper}>
				<h2 className={styles.title}>G(x)</h2>
				<Dropdown // TODO
					className={styles.dropdown}
					name='Выберите g(x)'
					value={functionConfig.config.outer}
					setValue={(val) => dispatch(setOuter(val))}
					content={functions}
				/>
			</div>

			<div className={styles.column_wrapper}>
				<h2 className={styles.title}>F(G(x))</h2>
				<VerifiedInput
					className={styles.input}
					value={functionConfig.config.name}
					setValue={(val) => dispatch(setName(val))}
					checkCorrect={(text) => !!text}
					placeholder='Имя функции'
					type='text'
				/>
				<CreateButton text="Создать" onClick={createCompositeFunc} disabled={!isCorrect} />
			</div>
		</div>
	)
}
