"use client";
import VerifiedInput from "@/components/VerifiedInput";
import styles from "./index.module.css";
import Dropdown from "@/components/Dropdown";
import { countVerifier, floatVerifier } from "@/utils/verifiers";
import { setCount, setEnd, setFunction, setStart } from "@/store/slices/functionConfigSlice";
import { useDispatch, useSelector } from "react-redux";
import { useEffect, useState } from "react";
import instance from "@/utils/axiosInstance";
import CreateButton from "../CreateButton";

export default () => {
	const functionConfig = useSelector((state) => state.simpleFunctionConfig);
    const dispatch = useDispatch();

    const [functions, setFunctions] = useState([])
    
    useEffect(() => {
        instance.get("/tabulated/simple").then((response) => {
            setFunctions(response.data.map(func => ({text: func.canonical_name, value: func.type})))
        })
    }, []);

    const onCreate = () => {
        instance.post("/tabulated/current/simple", functionConfig).then((response) => {
            console.log(response);
        }).catch(error => console.log(error))
    }

    return (
        <fieldset className={styles.form}>
            <legend className={styles.legend}>ArrayTabulatedFunction</legend>
            <div className={styles.container}>
                <p className={styles.property}>Простая функция</p>
                <Dropdown
                    className={styles.dropdown}
                    name="Выберите фунцкию"
                    value={functionConfig.function}
                    setValue={(val) => dispatch(setFunction(val))}
                    content={functions}
                />
                <p className={styles.property}>Начало области</p>
                <VerifiedInput value={functionConfig.start} setValue={(val) => dispatch(setStart(val))} getError={(text, firstTime) => floatVerifier(text, firstTime, (val) => dispatch(setStart(val)))}/>
                <p className={styles.property}>Конец области</p>
                <VerifiedInput value={functionConfig.end} setValue={(val) => dispatch(setEnd(val))} getError={(text, firstTime) => floatVerifier(text, firstTime, (val) => dispatch(setEnd(val)))}/>
                <p className={styles.property}>Количество точек</p>
                <VerifiedInput value={functionConfig.count} setValue={(val) => dispatch(setCount(val))} getError={(text, firstTime) => countVerifier(text, firstTime, (val) => dispatch(setCount(val)))}/>
            </div>
            <CreateButton onClick={onCreate}/>
        </fieldset>
    );
};
