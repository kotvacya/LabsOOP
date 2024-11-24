import Dropdown from '@/components/Dropdown'
import styles from './index.module.css'

export default ({ state, setState }) => {
	const operations = [
		{ text: '+', value: '+' },
		{ text: '-', value: '-' },
		{ text: '⨉', value: '*' },
		{ text: '÷', value: '/' },
		{ text: '𝑑', value: 'd' },
	]

	return (
		<Dropdown
			className={styles.dropdown}
			content={operations}
			name={'+'}
			value={state}
			setValue={setState}
		/>
	)
}
