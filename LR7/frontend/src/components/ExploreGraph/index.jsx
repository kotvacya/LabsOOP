import VerifiedInput from '@/components/VerifiedInput'
import { operandInsert } from '@/store/slices/operandSlice'
import instance from '@/utils/axiosInstance'
import classNames from '@/utils/classNames'
import { floatVerifier } from '@/utils/verifiers'
import { useState } from 'react'
import { useDispatch } from 'react-redux'
import styles from './index.module.css'

export default ({ insertable }) => {
	const dispatch = useDispatch()
	const [input, setInput] = useState({ x: '', y: '' })

	const setX = (v) => setInput((prev) => ({ ...prev, x: v }))
	const setY = (v) => setInput((prev) => ({ ...prev, y: v }))

	const isCorrect = floatVerifier(input.x) && floatVerifier(input.y)
	const isCorrectX = floatVerifier(input.x)

	const onApply = async (e) => {
		e.preventDefault()
		const response = await instance.get('/tabulated/operands/3/apply', {
			params: { x: input.x },
		})
		setY(response.data)
	}

	const onPaste = (e) => {
		e.preventDefault()
		dispatch(operandInsert({ func_id: 3, x: input.x, y: input.y }))
	}

	return (
		<>
			<VerifiedInput
				value={input.x}
				setValue={setX}
				checkCorrect={floatVerifier}
				className={styles.input}
				placeholder='X:'
			/>
			<VerifiedInput
				value={input.y}
				setValue={setY}
				checkCorrect={floatVerifier}
				className={classNames(styles.input, !insertable && styles.disabled)}
				placeholder='Y:'
			/>
			<div className={styles.btns}>
				<button
					className={classNames(styles.btn, !isCorrectX && styles.disabled)}
					onClick={onApply}
				>
					Вычислить
				</button>

				{insertable && (
					<button
						className={classNames(styles.btn, !isCorrect && styles.disabled)}
						onClick={onPaste}
					>
						Вставить
					</button>
				)}
			</div>
		</>
	)
}
