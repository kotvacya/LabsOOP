import CreateButton from '../CreateButton'
import AddButton from './AddButton'
import CountInput from './CountInput'
import Dot from './Dot'
import styles from './index.module.css'

export default () => {
	const addDot = () => {}
	return (
		<div className={styles.wrapper}>
			<div className={styles.dotes}>
				<Dot />

				<AddButton onClick={addDot} />
			</div>
			<div className={styles.controls}>
				<CountInput />
				<CreateButton />
			</div>
		</div>
	)
}
