'use client'
import Dropdown from '@/components/Dropdown'
import VerifiedInput from '@/components/VerifiedInput'
import { useState } from 'react'
import { useDispatch, useSelector } from 'react-redux'
import CreateButton from '../CreateButton'
import styles from './index.module.css'

export default () => {
	const dispatch = useDispatch()
	const [text, setText] = useState('')
	const functionConfig = useSelector((state) => state.simpleFunctionConfig)

	const isCorrect = !!text
	const createCompositeFunc = async () => {
		//const response = await instance.post('/tabulated/current/simple', functionConfig.config)
		//console.log(response)
	}

	return (
		<div className={styles.wrapper}>
			<div className={styles.column_wrapper}>
				<h2 className={styles.title}>F(x)</h2>
				<Dropdown // TODO
					className={styles.dropdown}
					name='Выберите f(x)'
					value={functionConfig.config.function}
					setValue={(val) => dispatch(setFunction(val))}
					content={functionConfig.functions}
				/>
			</div>

			<div className={styles.column_wrapper}>
				<h2 className={styles.title}>G(x)</h2>
				<Dropdown // TODO
					className={styles.dropdown}
					name='Выберите g(x)'
					value={functionConfig.config.function}
					setValue={(val) => dispatch(setFunction(val))}
					content={functionConfig.functions}
				/>
			</div>

			<div className={styles.column_wrapper}>
				<h2 className={styles.title}>F(G(x))</h2>
				<VerifiedInput
					className={styles.input}
					value={text}
					setValue={setText}
					checkCorrect={(text) => !!text}
					placeholder='Имя функции'
					type='text'
				/>
				<CreateButton create={createCompositeFunc} disabled={!isCorrect} type={1} />
			</div>
		</div>
	)
}
