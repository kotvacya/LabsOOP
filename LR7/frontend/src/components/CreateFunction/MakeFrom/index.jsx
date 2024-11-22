'use client'
import classNames from '@/utils/classNames'
import styles from './index.module.css'

export default ({ state, setState }) => {
	const arraysClick = () => setState(false)
	const functionClick = () => setState(true)

	return (
		<div className={styles.wrapper}>
			<h1 className={styles.title}>Создать функцию из: </h1>
			<div className={styles.buttons}>
				<button
					className={classNames(styles.btn, !state && styles.btn_active)}
					onClick={arraysClick}
				>
					массивов
				</button>
				<span className={styles.br}>|</span>
				<button
					className={classNames(styles.btn, state && styles.btn_active)}
					onClick={functionClick}
				>
					функции
				</button>
			</div>
		</div>
	)
}
