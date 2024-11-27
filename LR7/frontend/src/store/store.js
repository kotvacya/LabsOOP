import { configureStore } from '@reduxjs/toolkit'
import ArrayPointsSlice from './slices/arrayPointsSlice'
import modalSlice from './slices/confirmationModalSlice'
import factorySlice from './slices/factoryTypeSlice'
import operandSlice from './slices/operandSlice'
import operatorSlice from './slices/operatorSlice'
import SimpleFunctionsConfigSlice from './slices/SimpleFunctionSlice'

export default configureStore({
	reducer: {
		arrayPoints: ArrayPointsSlice,
		simpleFunctionConfig: SimpleFunctionsConfigSlice,
		factory: factorySlice,
		operator: operatorSlice,
		operands: operandSlice,
		popups: modalSlice,
	},
})
