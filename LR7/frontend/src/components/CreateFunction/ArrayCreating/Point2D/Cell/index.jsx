'use client'
import NumberInput from '@/components/CreateFunction/NumberInput'
import styles from "./index.module.css"

function errorFunc(text){
	let float = parseFloat(text)
	if(float !== float){
		return "error"
	}
}

export default () => {
	return <NumberInput className={styles.input} step={"any"} getError={errorFunc} />
}
