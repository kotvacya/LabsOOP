'use client'
import CountInput from "@/components/CreateFunction/CountInput";
import React from "react";
import CreateButton from "../CreateButton";
import { useDispatch, useSelector } from "react-redux";
import { setPointCount } from "@/store/slices/pointsSlice";
import ArrayFunction from "@/components/ArrayFunction";
import styles from "./index.module.css"
import instance from "@/utils/axiosInstance";

export default function NewFuncArray() {
    const points = useSelector((state) => state.arrayPoints.points);
    const dispatch = useDispatch();

    const onCreate = () => {
        instance.post("/tabulated/current/array", {points: points}).then((response) => {
            console.log(response);
        }).catch(error => console.log(error))
    }

    return (
        <div className={styles.wrapper}>
            <ArrayFunction/>    
            <div className={styles.controls}>
                <CountInput
                    count={points.length}
                    setCount={(count) => dispatch(setPointCount(count))}
                />
                <CreateButton onClick={onCreate}/>
            </div>
        </div>
    );
}
