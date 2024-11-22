'use client'
import classNames from '@/utils/classNames'
import { useState } from 'react'
import styles from './index.module.css'

export default ({ inputClass }) => {
	const [text, setText] = useState('')
	const onChange = (e) => {
		if (Number(e.target.value) > 0) setText(e.target.value)
	}

	return (
		<input
			type='number'
			className={classNames(styles.input, inputClass)}
			value={text}
			onChange={onChange}
		/>
	)
}
