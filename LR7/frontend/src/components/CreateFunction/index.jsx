'use client'
import { useState } from 'react'
import ArrayCreating from '../ArrayFunction'
import FunctionCreating from './FunctionCreating'
import styles from './index.module.css'
import MakeFrom from './MakeFrom'
import NewFuncArray from './NewFuncArray'

export default () => {
	const [choice, setChoice] = useState(false)
	return (
		<div className={styles.wrapper}>
			<MakeFrom state={choice} setState={setChoice} />
			{choice ? <FunctionCreating /> : <NewFuncArray />}
		</div>
	)
}
