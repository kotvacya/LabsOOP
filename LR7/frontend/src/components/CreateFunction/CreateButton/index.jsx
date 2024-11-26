'use client'
import classNames from '@/utils/classNames'
import { useSelector } from 'react-redux'
import styles from './index.module.css'

export default ({ className, text, ...rest }) => {
	const copyto = useSelector((state) => state.operands.copyto)

	return (
		<button className={classNames(className, styles.btn)} {...rest}>
			{copyto !== null ? `Создать ${copyto + 1} операнд` : 'Сохранить'}
		</button>
	)
}
