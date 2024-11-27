'use client'
import OperandFunction from '@/components/OperandFunction'
import VerifiedInput from '@/components/VerifiedInput'
import classNames from '@/utils/classNames'
import { floatVerifier } from '@/utils/verifiers'
import { useState } from 'react'
import Chart from '../../components/Chart'
import styles from './page.module.css'

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
	const [points, setPoints] = useState(arr) // TODO
	const [input, setInput] = useState({ x: '', y: '' })

	const setX = (v) => setInput((prev) => ({ ...prev, x: v }))
	const setY = (v) => setInput((prev) => ({ ...prev, y: v }))

	const isCorrect = floatVerifier(input.x) && floatVerifier(input.y)
	const isCorrectX = floatVerifier(input.x)

	const onApply = (e) => {
		e.preventDefault()
		setY(mock.apply(input.x)) // TODO
	}

	const onPaste = (e) => {
		e.preventDefault()
		setPoints((prev) => [...prev, { id: id--, x: input.x, y: input.y }]) // TODO set(new_arr)
	}

	return (
		<div className={styles.wrapper}>
			<OperandFunction points={points} />

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
				<Chart
					className={styles.graph}
					xData={points.map((el) => el.x)}
					yData={points.map((el) => el.y)}
				/>
			</div>
		</div>
	)
}
