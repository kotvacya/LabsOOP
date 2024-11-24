import OperandFunction from '@/components/OperandFunction'
import ApplyButton from '@/components/OperandFunction/Buttons/ApplyButton'
import OperatorButton from '@/components/OperandFunction/Buttons/OperatorButton'
import styles from './page.module.css'

export default () => {
	const points = [{ id: 1, x: 1, y: 1 }]
	return (
		<div className={styles.wrapper}>
			<OperandFunction points={points} id={0} />
			<OperatorButton />
			<OperandFunction points={points} id={1} />
			<ApplyButton />
			<OperandFunction points={points} id={2} immutable />
		</div>
	)
}
