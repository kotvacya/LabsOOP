import styles from './index.module.css'

export default ({...rest}) => {
	return <button {...rest} className={styles.button}>=</button>
}
