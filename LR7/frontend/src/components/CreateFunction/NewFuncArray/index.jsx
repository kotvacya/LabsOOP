'use client'
import ArrayFunction from '@/components/ArrayFunction'
import CountInput from '@/components/CreateFunction/NewFuncArray/CountInput'
import { addPoint, removePoint, updatePoint } from '@/store/slices/arrayPointsSlice'
import { createTimeLimitedPopup } from '@/store/slices/confirmationModalSlice'
import instance from '@/utils/axiosInstance'
import { useDispatch, useSelector } from 'react-redux'
import CreateButton from '../CreateButton'
import styles from './index.module.css'

export default function NewFuncArray({ doCopy, createText }) {
	const factory = useSelector((state) => state.factory)

	const points = useSelector((state) => state.arrayPoints.points)
	const dispatch = useDispatch()

	const checkPoints = () => {
		if (points.length == 0) return true
		for (let el of points) if (isNaN(parseFloat(el.x)) || isNaN(parseFloat(el.y))) return true
		return false
	}

	const onChangeX = (id, val) => dispatch(updatePoint({ id: id, x: val }))
	const onChangeY = (id, val) => dispatch(updatePoint({ id: id, y: val }))
	const onRemove = (id) => dispatch(removePoint(id))
	const onAdd = () => dispatch(addPoint())

	const createArrayFunc = async () => {
		const response = await instance.post('/tabulated/current/array', { points: points })
		if (doCopy) await doCopy()
		dispatch(
			createTimeLimitedPopup({ success: true, message: 'Функция успешно создана', duration: 5 })
		)
	}

	return (
		<div className={styles.wrapper}>
			<ArrayFunction
				points={points}
				onChangeX={onChangeX}
				onChangeY={onChangeY}
				onRemove={onRemove}
				onAdd={onAdd}
				factory={factory?.current?.replace(/factory/i, '') || 'TabulatedFunction'}
			/>
			<div className={styles.controls}>
				<CountInput currentCount={points.length} />
				<CreateButton
					onClick={createArrayFunc}
					text={createText || 'Создать'}
					disabled={checkPoints()}
				/>
			</div>
		</div>
	)
}
