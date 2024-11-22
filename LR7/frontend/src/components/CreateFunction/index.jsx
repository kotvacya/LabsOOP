'use client'
import { useState } from 'react'
import ArrayCreating from './ArrayCreating'
import FunctionCreating from './FunctionCreating'
import styles from './index.module.css'
import MakeFrom from './MakeFrom'

export default () => {
	const [choice, setChoice] = useState(false)
	return (
		<div className={styles.wrapper}>
			<MakeFrom state={choice} setState={setChoice} />
			{choice ? <FunctionCreating /> : <ArrayCreating />}
		</div>
	)
}
