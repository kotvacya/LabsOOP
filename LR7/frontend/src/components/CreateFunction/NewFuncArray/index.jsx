'use client'
import ArrayFunction from '@/components/ArrayFunction'
import CountInput from '@/components/CreateFunction/CountInput'
import { addPoint, removePoint, setPointCount, updatePoint } from '@/store/slices/pointSlice'
import instance from '@/utils/axiosInstance'
import { useDispatch, useSelector } from 'react-redux'
import CreateButton from '../CreateButton'
import styles from './index.module.css'

export default function NewFuncArray({ onCreate }) {
	const points = useSelector((state) => state.arrayPoints.points)
	const dispatch = useDispatch()

	const onChangeX = (pt, val) => dispatch(updatePoint({ id: pt.id, x: val }))
	const onChangeY = (pt, val) => dispatch(updatePoint({ id: pt.id, y: val }))

	const onCreateClick = async () => {
		const response = await instance.post('/tabulated/current/array', { points: points })
		console.log(response)
		await onCreate()
	}

	return (
		<div className={styles.wrapper}>
			<ArrayFunction
				points={points}
				onChangeX={onChangeX}
				onChangeY={onChangeY}
				onRemove={(pt) => dispatch(removePoint(pt.id))}
				onAdd={() => dispatch(addPoint())}
			/>
			<div className={styles.controls}>
				<CountInput count={points.length} setCount={(count) => dispatch(setPointCount(count))} />
				<CreateButton onClick={onCreateClick} />
			</div>
		</div>
	)
}
