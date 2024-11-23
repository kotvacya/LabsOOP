import Cell from './Cell'
import Trashcan from './Trashcan'
import styles from './index.module.css'

export default ({onRemove, onChange, values}) => {
	return (
		<div className={styles.wrapper}>
			<Cell value={values.x} onChange={(val) => (onChange("x", val))}/>
			<Cell value={values.y} onChange={(val) => (onChange("y", val))}/>
			<Trashcan onClick={onRemove} />
		</div>
	)
}
