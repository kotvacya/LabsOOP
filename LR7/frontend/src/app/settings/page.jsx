'use client'
import Dropdown from '@/components/Dropdown'
import { setFactory } from '@/store/slices/factoryTypeSlice'
import { useDispatch, useSelector } from 'react-redux'
import styles from './page.module.css'

export default () => {
	const factoryInfo = useSelector((state) => state.factory)
	const dispatch = useDispatch()

	return (
		<div className={styles.wrapper}>
			<h1 className={styles.title}>Создавать функции как: </h1>
			<Dropdown
				name='Выберите фабрику'
				className={styles.dropdown}
				value={factoryInfo.current}
				setValue={(val) => dispatch(setFactory(val))}
				content={factoryInfo.all}
			/>
		</div>
	)
}
