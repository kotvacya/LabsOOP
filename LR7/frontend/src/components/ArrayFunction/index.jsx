'use client'
import { addPoint, removePoint, updatePoint } from '@/store/slices/pointSlice'
import { useDispatch, useSelector } from 'react-redux'
import AddButton from './AddButton'
import Point2D from './Point2D'
import styles from './index.module.css'

export default () => {
	const points = useSelector((state) => state.arrayPoints.points)
	const dispatch = useDispatch()

	// console.log(points);

	return (
		<fieldset className={styles.points}>
			<legend className={styles.legend}>ArrayTabulatedFunction</legend>

			{points.map((pt) => (
				<Point2D
					key={pt.id}
					values={pt}
					onChange={(p, val) => dispatch(updatePoint({ id: pt.id, [p]: val }))}
					onRemove={() => dispatch(removePoint(pt.id))}
				/>
			))}

			<AddButton onClick={() => dispatch(addPoint())} />
		</fieldset>
	)
}
