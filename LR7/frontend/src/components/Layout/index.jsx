'use client'
import { useSelector } from 'react-redux'
import { CSSTransition, TransitionGroup } from 'react-transition-group'
import InsertModal from '../InsertModal'
import ResultModal from '../ResultModal'
import Header from './Header'
import styles from './index.module.css'

export default ({ children }) => {
	const popups = useSelector((state) => state.popups)

	return (
		<div className={styles.wrapper}>
			<Header />
			<div className={styles.container}>{children}</div>
			<TransitionGroup className={styles.popups}>
				{popups.map((el) => (
					<CSSTransition
						key={el.id}
						timeout={200} // Время анимации
						classNames={{
							enter: styles['popups-enter'],
							enterActive: styles['popups-enter-active'],
							exit: styles['popups-exit'],
							exitActive: styles['popups-exit-active'],
						}}
					>
						<ResultModal id={el.id} success={el.success} message={el.message} />
					</CSSTransition>
				))}
			</TransitionGroup>
			<InsertModal />
		</div>
	)
}
