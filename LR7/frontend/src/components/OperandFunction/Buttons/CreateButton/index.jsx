import styles from './index.module.css'

export default ({ ...rest }) => {
	return (
		<button {...rest} className={styles.button}>
			<svg width='32' height='32' viewBox='0 0 32 32' fill='black' stroke='black' strokeWidth='0.5'>
				<path d='M26,26H6V6H16V4H6A2.002,2.002,0,0,0,4,6V26a2.002,2.002,0,0,0,2,2H26a2.002,2.002,0,0,0,2-2V16H26Z' />
				<path d='M26,26H6V6H16V4H6A2.002,2.002,0,0,0,4,6V26a2.002,2.002,0,0,0,2,2H26a2.002,2.002,0,0,0,2-2V16H26Z' />
				<polygon points='26 6 26 2 24 2 24 6 20 6 20 8 24 8 24 12 26 12 26 8 30 8 30 6 26 6' />
			</svg>
		</button>
	)
}
