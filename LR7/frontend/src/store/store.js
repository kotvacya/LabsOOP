import { configureStore } from '@reduxjs/toolkit'
import ArrayPointsSlice from './slices/arrayPointsSlice'
import modalSlice from './slices/confirmationModalSlice'
import factorySlice from './slices/factoryTypeSlice'
import insertModalSlice from './slices/insertModalSlice'
import operandSlice from './slices/operandSlice'
import operatorSlice from './slices/operatorSlice'
import SimpleFunctionsConfigSlice from './slices/SimpleFunctionSlice'
import CompositeFunctionSlice from './slices/CompositeFunctionSlice'

export default configureStore({
	reducer: {
		arrayPoints: ArrayPointsSlice,
		simpleFunctionConfig: SimpleFunctionsConfigSlice,
		compositeFunctionConfig: CompositeFunctionSlice,
		factory: factorySlice,
		operator: operatorSlice,
		operands: operandSlice,
		popups: modalSlice,
		insert: insertModalSlice,
	},
})
