import classNames from '@/utils/classNames'
import styles from './index.module.css'

export default function Dropdown({content, name, value, setValue, className}) {
    const text = content.find((data) => data.value === value)?.text || name;
    
    return <div className={classNames(styles.dropdown, className)}>
        <button className={styles.dropbtn}>{text}</button>
        <div className={styles.dropdown_content}>
            {content.map((el, i) => (
                <button className={styles.elementbtn} onClick={() => {setValue(el.value)}} key={i}>{el.text}</button>
            ))}
        </div>
    </div>;
}
