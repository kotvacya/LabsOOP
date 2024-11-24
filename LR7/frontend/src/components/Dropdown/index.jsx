import classNames from '@/utils/classNames'
import styles from './index.module.css'

export default function Dropdown({ content, name, value, setValue, className }) {
	const text = content.find((data) => data.value === value)?.text || name

	return (
		<div className={classNames(styles.dropdown, className)}>
			<button className={styles.drop_btn}>{text}</button>
			<div className={styles.dropdown_content}>
				{content.map((el, i) => (
					<button
						className={classNames(styles.element_btn, i == content.length - 1 && styles.last)}
						onClick={() => setValue(el.value)}
						key={i}
					>
						{el.text}
					</button>
				))}
			</div>
		</div>
	)
}
