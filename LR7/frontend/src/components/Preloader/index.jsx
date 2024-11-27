'use client'
import { fetchCurrentFunction } from '@/store/slices/arrayPointsSlice'
import { createTimeLimitedPopup } from '@/store/slices/confirmationModalSlice'
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
			window.addEventListener("unhandledrejection", function(promiseRejectionEvent) { 
				const error = promiseRejectionEvent.reason	
				dispatch(createTimeLimitedPopup({ success: false, message: error.response?.data?.error || error, duration: 5 }))
			});

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
