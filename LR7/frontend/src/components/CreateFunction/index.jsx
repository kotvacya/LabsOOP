'use client'
import { useState } from 'react'
import NewFuncSimple from './NewFuncSimple'
import styles from './index.module.css'
import MakeFrom from './MakeFrom'
import NewFuncArray from './NewFuncArray'

export default () => {
	const [choice, setChoice] = useState(false)
	return (
		<div className={styles.wrapper}>
			<MakeFrom state={choice} setState={setChoice} />
			{choice ? <NewFuncSimple /> : <NewFuncArray />}
		</div>
	)
}
