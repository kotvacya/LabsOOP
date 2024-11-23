'use client'
import classNames from '@/utils/classNames'
import { useState } from 'react'
import styles from './index.module.css'

export default ({ className, getError, value, setValue, ...rest }) => {
	const [text, setText] = setValue ? [value, setValue] : useState(value || "")
	const [error, setError] = useState(getError ? getError(text) : null)
	
	const onChange = getError ? (e) => {
		setText(e.target.value);
		const err = getError(e.target.value);
		setError(err)
	} : (e) => {setText(e.target.value)}
	
	return (<>
		<input
			type='number'
			className={classNames(styles.input, className, error ? styles.invalid : null)}
			value={text}
			onChange={onChange}
			{...rest}
		/>
		
		</>
	)
}
