'use client'
import AddButton from './AddButton'
import Point2D from './Point2D'
import styles from './index.module.css'

export default ({ points, onRemove, onChangeX, onChangeY, onAdd }) => {
	return (
		<fieldset className={styles.points}>
			<legend className={styles.legend}>ArrayTabulatedFunction</legend>
			{points.map((pt) => (
				<Point2D
					key={pt.id}
					values={pt}
					onChangeX={onChangeX ? (val) => onChangeX(pt, val) : undefined}
					onChangeY={onChangeY ? (val) => onChangeY(pt, val) : undefined}
					onRemove={onRemove ? () => onRemove(pt) : undefined}
				/>
			))}
			{onAdd && <AddButton onClick={onAdd} />}
		</fieldset>
	)
}
