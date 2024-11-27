'use client'
import classNames from '@/utils/classNames'
import AddButton from './AddButton'
import Point2D from './Point2D'
import styles from './index.module.css'

export default ({ points, onChangeX, onChangeY, onRemove, onAdd, className, factory }) => {
	return (
		<fieldset className={classNames(styles.points, className)}>
			<legend className={styles.legend}>{factory}</legend>
			<div className={styles.wrapper}>
				{points?.map((pt) => (
					<Point2D
						key={pt.id}
						values={pt}
						onChangeX={onChangeX ? (val) => onChangeX(pt.id, val) : undefined}
						onChangeY={onChangeY ? (val) => onChangeY(pt.id, val) : undefined}
						onRemove={onRemove ? () => onRemove(pt.id) : undefined}
					/>
				))}
			</div>
			{onAdd && <AddButton onClick={onAdd} />}
		</fieldset>
	)
}
