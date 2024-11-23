'use client'
import { useEffect, useState } from 'react'
import CreateButton from '../CreateButton'
import AddButton from './AddButton'
import CountInput from './CountInput'
import Point2D from './Point2D'
import styles from './index.module.css'

export default () => {
	const [points, setPoints] = useState([]);
	const [index, setIndex] = useState(0);
	const [count, setCount] = useState(0);

	useEffect(() => {
		let diff = Math.max(0, count - points.length)
		setPoints([ ...points.slice(0, count), ...Array(diff).fill().map((v,i)=>({key: index+i})) ].map(
			(p, i) => ({...p, index: i})
		))
		setIndex(index + diff)
	}, [count]);

	const addPoint = () => {
		setCount(points.length + 1)
		setPoints([...points, {key: index}].map(
			(p, i) => ({...p, index: i})
		))
		setIndex(index + 1)
	}

	const removePoint = (idx) => {
		setCount(points.length - 1)
		setPoints(points.filter((p, i) => i !== idx).map(
			(p, i) => ({...p, index: i})
		))
	}

	console.log(points)

	return (
		<div className={styles.wrapper}>
			<fieldset className={styles.points}>
				<legend className={styles.legend}>ArrayTabulatedFunction</legend>
				
				{
				points.map((pt) => 
					<Point2D key={pt.key} onRemove={() => removePoint(pt.index)}/>
				)
				}

				<AddButton onClick={addPoint} />
			</fieldset>
			<div className={styles.controls}>
				<CountInput count={count} setCount={setCount} />
				<CreateButton />
			</div>
		</div>
	)
}
