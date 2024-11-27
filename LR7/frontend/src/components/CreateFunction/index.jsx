'use client'
import { useState } from 'react'
import styles from './index.module.css'
import MakeFrom from './MakeFrom'
import NewFuncArray from './NewFuncArray'
import NewFuncComposite from './NewFuncComposite'
import NewFuncSimple from './NewFuncSimple'

export default () => {
	const [choice, setChoice] = useState(0)

	return (
		<div className={styles.wrapper}>
			<MakeFrom state={choice} setState={setChoice} />
			{choice == 0 && <NewFuncArray />}
			{choice == 1 && <NewFuncSimple />}
			{choice == 2 && <NewFuncComposite />}
		</div>
	)
}
