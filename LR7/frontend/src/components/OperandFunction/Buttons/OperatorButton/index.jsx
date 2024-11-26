'use client'
import Dropdown from '@/components/Dropdown'
import { setOperator } from '@/store/slices/operatorSlice'
import classNames from '@/utils/classNames'
import { useDispatch, useSelector } from 'react-redux'
import styles from './index.module.css'

export default ({ className }) => {
	const dispatch = useDispatch()
	const operatorConfig = useSelector((state) => state.operator)

	return (
		<Dropdown
			className={classNames(styles.dropdown, className)}
			content={operatorConfig.all}
			value={operatorConfig.current}
			setValue={(val) => dispatch(setOperator(val))}
		/>
	)
}
