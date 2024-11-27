'use client'
import ArrayFunction from '@/components/ArrayFunction'
import CountInput from '@/components/CreateFunction/NewFuncArray/CountInput'
import { addPoint, removePoint, updatePoint } from '@/store/slices/arrayPointsSlice'
import instance from '@/utils/axiosInstance'
import { useDispatch, useSelector } from 'react-redux'
import CreateButton from '../CreateButton'
import styles from './index.module.css'

export default function NewFuncArray() {
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
		console.log(response)
	}

	return (
		<div className={styles.wrapper}>
			<ArrayFunction
				points={points}
				onChangeX={onChangeX}
				onChangeY={onChangeY}
				onRemove={onRemove}
				onAdd={onAdd}
			/>
			<div className={styles.controls}>
				<CountInput currentCount={points.length} />
				<CreateButton create={createArrayFunc} disabled={checkPoints()} />
			</div>
		</div>
	)
}
