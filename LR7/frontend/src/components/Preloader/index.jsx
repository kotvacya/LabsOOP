'use client'
import { fetchCurrentFunction } from '@/store/slices/arrayPointsSlice'
import { fetchAllFactories, fetchCurrentFactory } from '@/store/slices/factoryTypeSlice'
import { fetchAllOperands } from '@/store/slices/operandSlice'
import { fetchAllOperators } from '@/store/slices/operatorSlice'
import { fetchSimpleFunctions } from '@/store/slices/SimpleFunctionSlice'
import { useEffect, useRef } from 'react'
import { useDispatch } from 'react-redux'

export default function Preloader() {
	const dispatch = useDispatch()

	const isMounted = useRef(false)

	useEffect(() => {
		if (!isMounted.current) {
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
