import Dropdown from '@/components/Dropdown'
import styles from './index.module.css'
import { useDispatch, useSelector } from 'react-redux'
import { setOperator } from '@/store/slices/operatorSlice'

export default () => {
	const dispatch = useDispatch()
	const operatorConfig = useSelector(state => state.operator)

	return (
		<Dropdown
			className={styles.dropdown}
			content={operatorConfig.all}
			value={operatorConfig.current}
			setValue={(val) => dispatch(setOperator(val))}
		/>
	)
}
