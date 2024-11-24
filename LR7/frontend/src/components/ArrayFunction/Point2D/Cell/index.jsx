'use client'
import NumberInput from '@/components/VerifiedInput'
import classNames from '@/utils/classNames'
import styles from './index.module.css'

export default ({ onChange, value }) => {
	const errorFunc = (text, firstTime) => {
		let float = parseFloat(text)
		if (float !== float) return 'error'
		if (!firstTime) onChange(float)
	}

	return (
		<NumberInput
			value={value}
			className={classNames(styles.input, !onChange && styles.input_disabled)}
			step={'any'}
			disabled={!onChange}
			getError={errorFunc}
		/>
	)
}
