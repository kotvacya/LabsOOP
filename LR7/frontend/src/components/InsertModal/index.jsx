'use client'
import { setInsertVisibility } from '@/store/slices/insertModalSlice'
import { operandInsert } from '@/store/slices/operandSlice'
import classNames from '@/utils/classNames'
import { floatVerifier } from '@/utils/verifiers'
import { useState } from 'react'
import { useDispatch, useSelector } from 'react-redux'
import VerifiedInput from '../VerifiedInput'
import styles from './index.module.css'

export default () => {
	const dispatch = useDispatch()
	const state = useSelector((state) => state.insert)
	const [dot, setDot] = useState({ x: null, y: null })
	const setX = (x) => setDot((prev) => ({ ...prev, x: x }))
	const setY = (y) => setDot((prev) => ({ ...prev, y: y }))

	const onInsert = () => {
		dispatch(operandInsert({ func_id: state.id, x: dot.x, y: dot.y }))
		onClose()
	}
	const onClose = () => dispatch(setInsertVisibility(false))

	const isCorrect = floatVerifier(dot.x) && floatVerifier(dot.y)

	return (
		<>
			{state.visible && <div className={styles.overlay} onClick={onClose} />}
			<div className={classNames(styles.wrapper, !state.visible && styles.hidden)}>
				<h1 className={styles.title}>Введите координаты точки: </h1>
				<VerifiedInput
					className={styles.input}
					value={dot.x}
					setValue={setX}
					checkCorrect={floatVerifier}
					placeholder='X:'
				/>
				<VerifiedInput
					className={styles.input}
					value={dot.y}
					setValue={setY}
					checkCorrect={floatVerifier}
					placeholder='Y:'
				/>
				<div className={styles.buttons_wrapper}>
					<button className={styles.button} onClick={onClose}>
						Отмена
					</button>
					<button
						className={classNames(styles.button, !isCorrect && styles.disabled)}
						onClick={onInsert}
					>
						Вставить
					</button>
				</div>
			</div>
		</>
	)
}
