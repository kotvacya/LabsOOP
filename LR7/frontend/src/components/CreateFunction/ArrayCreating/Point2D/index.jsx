import Cell from './Cell'
import Trashcan from './Trashcan'
import styles from './index.module.css'

export default ({onRemove}) => {
	return (
		<div className={styles.wrapper}>
			<Cell />
			<Cell />
			<Trashcan onClick={onRemove} />
		</div>
	)
}
