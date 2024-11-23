'use client'
import ArrayFunction from '@/components/ArrayFunction'
import CountInput from '@/components/ArrayFunction/CountInput'
import { setPointCount } from '@/store/slices/pointSlice'
import { useDispatch, useSelector } from 'react-redux'
import CreateButton from '../CreateButton'
import styles from './index.module.css'

export default function NewFuncArray() {
	const points = useSelector((state) => state.arrayPoints.points)
	const dispatch = useDispatch()

	return (
		<div className={styles.wrapper}>
			<ArrayFunction />
			<div className={styles.controls}>
				<CountInput count={points.length} setCount={(count) => dispatch(setPointCount(count))} />
				<CreateButton />
			</div>
		</div>
	)
}
