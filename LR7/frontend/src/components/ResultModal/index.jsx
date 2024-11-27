'use client'
import classNames from '@/utils/classNames'

import { deletePopup } from '@/store/slices/confirmationModalSlice'
import { useDispatch } from 'react-redux'
import styles from './index.module.css'

export default ({ id, success, message }) => {
	const dispatch = useDispatch()

	const close = (e) => {
		e.preventDefault()
		dispatch(deletePopup(id))
	}

	return (
		<div className={classNames(styles.wrapper, success ? styles.success : styles.fail)}>
			{message}
			<button className={styles.btn} onClick={close}>
				x
			</button>
		</div>
	)
}
