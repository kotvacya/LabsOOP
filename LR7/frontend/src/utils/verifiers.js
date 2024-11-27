export const floatVerifier = (text) => {
	let float = parseFloat(text)
	if (isNaN(float)) return false
	return true
}

export const intVerifier = (text) => {
	let int = parseInt(text)
	if (isNaN(int)) return false
	return true
}

export const countVerifier = (text) => {
	let int = parseInt(text)

	if (int !== int) return false
	if (int <= 0) return false

	return true
}
