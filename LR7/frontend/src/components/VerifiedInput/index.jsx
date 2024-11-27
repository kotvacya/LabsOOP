'use client'
import classNames from '@/utils/classNames'
import { useState } from 'react'
import styles from './index.module.css'

export default ({ className, getError, value, setValue, ...rest }) => {
	const val = value === undefined ? '' : value?.toString()
	const [text, setText] = setValue ? [val || '', setValue] : useState(val || '')
	const [error, setError] = useState(getError ? getError(text, true) : null)

	const onChange = getError
		? (e) => {
				setText(e.target.value)
				const err = getError(e.target.value)
				setError(err)
	}
		: (e) => {
				setText(e.target.value)
		}

	return (
		<input
			type='number'
			className={classNames(styles.input, className, error ? styles.invalid : null)}
			value={text}
			onChange={onChange}
			{...rest}
		/>
	)
}
