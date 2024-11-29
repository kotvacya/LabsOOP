'use client'
import classNames from '@/utils/classNames'
import styles from './index.module.css'
import { useState } from 'react'

export default function Dropdown({ content, name, value, setValue, className }) {
	const text = content.find((data) => data.value === value)?.text || name

	const [active, setActive] = useState(false);

	const onEnter = (e) => {
		e.preventDefault()
		setActive(true)
	}

	const onLeave = (e) => {
		e.preventDefault()
		setActive(false)
	}

	return (
		<div className={classNames(styles.dropdown, className)} onMouseEnter={onEnter} onMouseLeave={onLeave}>
			<button className={styles.drop_btn}>{text}</button>
			<div className={classNames(styles.dropdown_content, active && styles.dropdown_content_hover)}>
				{content.map((el, i) => (
					<button
						className={classNames(styles.element_btn, i == content.length - 1 && styles.last)}
						onClick={() => {setActive(false); setValue(el.value)}}
						key={i}
					>
						{el.text}
					</button>
				))}
			</div>
		</div>
	)
}
