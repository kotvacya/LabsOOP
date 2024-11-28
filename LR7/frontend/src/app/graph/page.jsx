'use client'
import OperandFunction from '@/components/OperandFunction'
import VerifiedInput from '@/components/VerifiedInput'
import instance from '@/utils/axiosInstance'
import classNames from '@/utils/classNames'
import { floatVerifier } from '@/utils/verifiers'
import { useState } from 'react'
import { useSelector } from 'react-redux'
import Chart from '../../components/Chart'
import styles from './page.module.css'

export default () => {
	const functions = useSelector((state) => state.operands.functions)
	const [input, setInput] = useState({ x: '', y: '' })

	const setX = (v) => setInput((prev) => ({ ...prev, x: v }))
	const setY = (v) => setInput((prev) => ({ ...prev, y: v }))

	const isCorrect = floatVerifier(input.x) && floatVerifier(input.y)
	const isCorrectX = floatVerifier(input.x)

	const onApply = async (e) => {
		e.preventDefault()
		const response = await instance.get('/tabulated/operands/3/apply', {
			params: {
				x: input.x,
			},
		})

		setY(response.data)
	}

	const onPaste = (e) => {
		e.preventDefault()
		setPoints((prev) => [...prev, { id: id--, x: input.x, y: input.y }]) // TODO set(new_arr)
	}

	const points = functions[3]?.points || []
	const insertable = functions[3]?.insertable || false

	return (
		<div className={styles.wrapper}>
			<div className={styles.operand}>
				<OperandFunction id={3} points={points} />
			</div>

			<div className={styles.apply}>
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
			</div>

			<div className={styles.chart}>
				{points.length != 0 && (
					<Chart
						className={styles.graph}
						xData={points.map((el) => el.x)}
						yData={points.map((el) => el.y)}
					/>
				)}
				{points.length == 0 && 'Здесь будет график...'}
			</div>
		</div>
	)
}
