'use client'
import Dropdown from '@/components/Dropdown'
import { fetchFactory, setFactory } from '@/store/slices/factorySlice'
import { useDispatch, useSelector } from 'react-redux'
import styles from './page.module.css'
import instance from '@/utils/axiosInstance'
import { useEffect, useState } from 'react'

export default () => {
	const factory = useSelector((state) => state.factory)
	const dispatch = useDispatch()

	const [factoies, setFactories] = useState([])

	useEffect(() => {
		instance.get("/tabulated/factory/all").then((response) => {
			setFactories(response.data.map(func => ({text: func.canonical_name, value: func.type})))
		})
		instance.get("/tabulated/factory").then((response) => {
			dispatch(setFactory(response.data.type))
		})
	}, [])

	return (
		<div className={styles.wrapper}>
			<h1 className={styles.title}>Создавать функции как: </h1>
			<Dropdown
				name='Выберите фабрику'
				className={styles.dropdown}
				value={factory}
				setValue={(val) => dispatch(setFactory(val))}
				content={factoies}
			/>
		</div>
	)
}
