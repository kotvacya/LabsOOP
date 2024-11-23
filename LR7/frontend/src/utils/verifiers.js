export const floatVerifier = (text, firstTime, onChange) => {
    let float = parseFloat(text)
    if(float !== float){
        return "Не число"
    }
    if(!firstTime) onChange(float)
}

export const intVerifier = (text, firstTime, onChange) => {
    let int = parseInt(text)
    if(int !== int){
        return "Не число"
    }
    if(!firstTime) onChange(int)
}

export const countVerifier = (text, firstTime, onChange) => {
    let int = parseInt(text)
    if(int !== int){
        return "Не число"
    }else if(int <= 0){
        return "Число должно быть > 0"
    }
    if(!firstTime) onChange(int)
}
