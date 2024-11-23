'use client'
import CountInput from "@/components/ArrayFunction/CountInput";
import React from "react";
import CreateButton from "../CreateButton";
import { useDispatch, useSelector } from "react-redux";
import { setPointCount } from "@/store/slices/pointsSlice";
import ArrayFunction from "@/components/ArrayFunction";
import styles from "./index.module.css"

export default function NewFuncArray() {
    const points = useSelector((state) => state.arrayPoints.points);
    const dispatch = useDispatch();

    return (
        <div className={styles.wrapper}>
            <ArrayFunction/>    
            <div className={styles.controls}>
                <CountInput
                    count={points.length}
                    setCount={(count) => dispatch(setPointCount(count))}
                />
                <CreateButton />
            </div>
        </div>
    );
}
