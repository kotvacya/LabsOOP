import ActiveLink from '../ActiveLink'
import styles from './index.module.css'

export default () => {
	return (
		<header className={styles.header}>
			<ActiveLink href={'/'}>Создать</ActiveLink>
			<ActiveLink href={'/operations'}>Операции</ActiveLink>
			<ActiveLink href={'/graph'}>График</ActiveLink>
			<ActiveLink href={'/settings'}>Настройки</ActiveLink>
		</header>
	)
}