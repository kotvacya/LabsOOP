'use client'
import classNames from '@/utils/classNames'
import styles from './index.module.css'

export default ({ className, text, ...rest }) => {
	return <button className={classNames(className, styles.btn)} {...rest} >{text || "Создать"}</button>
}
