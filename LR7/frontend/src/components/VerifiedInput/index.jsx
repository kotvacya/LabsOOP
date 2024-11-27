'use client'
import classNames from '@/utils/classNames'
import styles from './index.module.css'

export default ({ value, setValue, checkCorrect, disabled, className, ...rest }) => {
	const onChange = (e) => {
		e.preventDefault()
		setValue(e.target.value)
	}

	return (
		<input
			type='number'
			className={classNames(
				styles.input,
				!checkCorrect(value) && styles.invalid,
				disabled && styles.disabled,
				className
			)}
			value={value || ''}
			onChange={onChange}
			{...rest}
		/>
	)
}
