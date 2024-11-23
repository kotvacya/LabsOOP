'use client'
import Dropdown from '@/components/Dropdown'
import { setFactory } from '@/store/slices/factorySlice'
import { useDispatch, useSelector } from 'react-redux'
import styles from './page.module.css'

export default () => {
	const factory = useSelector((state) => state.factory)
	const dispatch = useDispatch()

	return (
		<div className={styles.wrapper}>
			<h1 className={styles.title}>Создавать функции как: </h1>
			<Dropdown
				name='ArrayTabulatedFunction'
				className={styles.dropdown}
				value={factory}
				setValue={(val) => dispatch(setFactory(val))}
				content={[
					{ text: 'ArrayTabulatedFunction', value: false },
					{ text: 'LinkedListTabulatedFunction', value: true },
				]}
			/>
		</div>
	)
}
