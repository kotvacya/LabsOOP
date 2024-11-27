'use client'
import OperandFunction from '@/components/OperandFunction'
import VerifiedInput from '@/components/VerifiedInput'
import classNames from '@/utils/classNames'
import { floatVerifier } from '@/utils/verifiers'
import { useState } from 'react'
import Chart from '../../components/Chart'
import styles from './page.module.css'
import { useDispatch, useSelector } from 'react-redux'
import instance from '@/utils/axiosInstance'

//mocks
const insertable = true
const len = 9
const x = Array.from({ length: len }, (el, i) => i)
const y = Array.from({ length: len }, () => Math.random() * 10)
const arr = []
for (let i = 0; i < len; ++i) arr.push({ id: i, x: x[i], y: y[i] })
const mock = { apply: (el) => 20 }
let id = 0

export default () => {
	const dispatch = useDispatch()
	const functions = useSelector((state) => state.operands.functions)

	const [points, setPoints] = useState(arr) // TODO
	const [input, setInput] = useState({ x: '', y: '' })

	const setX = (v) => setInput((prev) => ({ ...prev, x: v }))
	const setY = (v) => setInput((prev) => ({ ...prev, y: v }))

	const isCorrect = floatVerifier(input.x) && floatVerifier(input.y)
	const isCorrectX = floatVerifier(input.x)

	const onApply = async (e) => {
		e.preventDefault()
		const response = await instance.get("/tabulated/operands/3/apply", {
			params: {
				x: input.x
			}
		})
		
		setY(response.data)
	}

	const onPaste = (e) => {
		e.preventDefault()
		setPoints((prev) => [...prev, { id: id--, x: input.x, y: input.y }]) // TODO set(new_arr)
	}

	const points2 = functions[3]?.points || []

	return (
		<div className={styles.wrapper}>
			<OperandFunction id={3} points={points2} />

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
				{points2.length !== 0 && <Chart
					className={styles.graph}
					xData={ points2.map((el) => el.x) }
					yData={ points2.map((el) => el.y) }
				/>} 
			</div>
		</div>
	)
}
