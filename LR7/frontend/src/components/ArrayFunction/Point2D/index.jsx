import Cell from './Cell'
import Trashcan from './Trashcan'
import styles from './index.module.css'

export default ({ onRemove, onChangeX, onChangeY, values }) => {
	return (
		<div className={styles.wrapper}>
			<Cell value={values.x} onChange={onChangeX} />
			<Cell value={values.y} onChange={onChangeY} />
			{onRemove && <Trashcan onClick={onRemove} />}
		</div>
	)
}
