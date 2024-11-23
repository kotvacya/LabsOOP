'use client'
import NumberInput from '@/components/VerifiedInput'
import styles from "./index.module.css"

export default ({onChange, value}) => {
	const errorFunc = (text, firstTime) => {
		let float = parseFloat(text)
		if(float !== float){
			return "error"
		}
		if(!firstTime) onChange(float)
		
	}

	return <NumberInput value={value} className={styles.input} step={"any"} getError={errorFunc} />
}
