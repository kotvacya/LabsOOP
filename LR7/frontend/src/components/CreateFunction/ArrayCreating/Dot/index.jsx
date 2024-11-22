import Cell from './Cell'
import Trashcan from './Trashcan'
import styles from './index.module.css'

export default () => {
	const deleteDot = () => {
		return
	}
	return (
		<div className={styles.wrapper}>
			<Cell />
			<Cell />
			<Trashcan onClick={deleteDot} />
		</div>
	)
}
