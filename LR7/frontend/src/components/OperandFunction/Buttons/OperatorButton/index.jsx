'use client'
import Dropdown from '@/components/Dropdown'
import { useState } from 'react'
import styles from './index.module.css'

export default () => {
	const [state, setState] = useState('+')

	const operations = [
		{ text: '+', value: '+' },
		{ text: '-', value: '-' },
		{ text: 'x', value: '*' },
		{ text: '÷', value: '/' },
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
