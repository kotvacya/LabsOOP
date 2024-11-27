'use client'
import VerifiedInput from '@/components/VerifiedInput'
import { floatVerifier } from '@/utils/verifiers'
import styles from './index.module.css'

export default ({ value, onChange }) => {
	return (
		<VerifiedInput
			value={value}
			setValue={onChange}
			className={styles.input}
			checkCorrect={floatVerifier}
			disabled={!onChange}
		/>
	)
}
