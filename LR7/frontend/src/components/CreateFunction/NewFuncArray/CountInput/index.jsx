'use client'
import VerifiedInput from '@/components/VerifiedInput'
import { setPointCount } from '@/store/slices/arrayPointsSlice'
import classNames from '@/utils/classNames'
import { countVerifier } from '@/utils/verifiers'
import { useEffect, useState } from 'react'
import { useDispatch } from 'react-redux'
import styles from './index.module.css'

export default ({ currentCount }) => {
	const dispatch = useDispatch()
	const [count, setCount] = useState()

	const isCorrect = countVerifier(count)

	const onClick = (e) => {
		e.preventDefault()
		if (isCorrect) dispatch(setPointCount(count))
	}

	useEffect(() => setCount(currentCount), [currentCount])

	return (
		<div className={styles.wrapper}>
			<span className={styles.label}>Количество точек:</span>
			<div className={styles.input_wrapper}>
				<VerifiedInput
					value={count}
					setValue={setCount}
					checkCorrect={countVerifier}
					className={styles.input}
				/>
				<button
					className={classNames(styles.btn, !isCorrect && styles.btn_disabled)}
					onClick={onClick}
				>
					{svg}
				</button>
			</div>
		</div>
	)
}

const svg = (
	<svg height='36' width='36' viewBox='0 0 492 492'>
		<path
			d='M484.128,104.478l-16.116-16.116c-5.064-5.068-11.816-7.856-19.024-7.856c-7.208,0-13.964,2.788-19.028,7.856
			L203.508,314.81L62.024,173.322c-5.064-5.06-11.82-7.852-19.028-7.852c-7.204,0-13.956,2.792-19.024,7.852l-16.12,16.112
			C2.784,194.51,0,201.27,0,208.47c0,7.204,2.784,13.96,7.852,19.028l159.744,159.736c0.212,0.3,0.436,0.58,0.696,0.836
			l16.12,15.852c5.064,5.048,11.82,7.572,19.084,7.572h0.084c7.212,0,13.968-2.524,19.024-7.572l16.124-15.992
			c0.26-0.256,0.48-0.468,0.612-0.684l244.784-244.76C494.624,132.01,494.624,114.966,484.128,104.478z'
		/>
	</svg>
)
