'use client'
import { fetchAllFactories, fetchCurrentFactory } from '@/store/slices/factorySlice'
import { fetchSimpleFunctions } from '@/store/slices/functionConfigSlice'
import { fetchAllOperands } from '@/store/slices/operandSlice'
import { fetchAllOperators } from '@/store/slices/operatorSlice'
import { fetchCurrentFunction } from '@/store/slices/pointSlice'
import React, { useEffect, useRef } from 'react'
import { useDispatch } from 'react-redux'

export default function Preloader() {
    const dispatch = useDispatch()

    const isMounted = useRef(false)

    useEffect(() => {
        if(!isMounted.current){
            dispatch(fetchAllFactories())
            dispatch(fetchCurrentFactory())
            dispatch(fetchSimpleFunctions())
            dispatch(fetchAllOperators())
            dispatch(fetchAllOperands())
            dispatch(fetchCurrentFunction())
        }
        isMounted.current = true
    }, [])

    return <></>
}
