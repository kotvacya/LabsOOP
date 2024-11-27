'use client'
import classNames from '@/utils/classNames'
import styles from './index.module.css'

export default ({ state, setState }) => {
	const arraysClick = () => setState(0)
	const functionClick = () => setState(1)
	const compositeClick = () => setState(2)

	return (
		<div className={styles.wrapper}>
			<h1 className={styles.title}>Создать функцию из: </h1>
			<div className={styles.buttons}>
				<button
					className={classNames(styles.btn, state == 0 && styles.btn_active)}
					onClick={arraysClick}
				>
					массива
				</button>
				<span className={styles.br}>|</span>
				<button
					className={classNames(styles.btn, state == 1 && styles.btn_active)}
					onClick={functionClick}
				>
					функции
				</button>
				<span className={styles.br}>|</span>
				<button
					className={classNames(styles.btn, state == 2 && styles.btn_active)}
					onClick={compositeClick}
				>
					композиции
				</button>
			</div>
		</div>
	)
}
