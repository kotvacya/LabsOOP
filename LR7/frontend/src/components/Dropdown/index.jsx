import React, { useState } from "react";
import styles from "./index.module.css"
import classNames from "@/utils/classNames";

export default function Dropdown({content, name, setValue, className}) {
    const [text, setText] = useState(name);
    return <div className={classNames(styles.dropdown, className)}>
        <button className={styles.dropbtn}>{text}</button>
        <div className={styles.dropdown_content}>
            {content.map((el, i) => (
                <button className={styles.elementbtn} onClick={() => {setValue(el.value); setText(el.text)}} key={i}>{el.text}</button>
            ))}
        </div>
    </div>;
}
