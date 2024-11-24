import { configureStore } from '@reduxjs/toolkit'
import factorySlice from './slices/factorySlice'
import SimpleFunctionsConfigSlice from './slices/functionConfigSlice'
import ArrayPointsSlice from './slices/pointSlice'
import operatorSlice from './slices/operatorSlice'

export default configureStore({
	reducer: {
		arrayPoints: ArrayPointsSlice,
		simpleFunctionConfig: SimpleFunctionsConfigSlice,
		factory: factorySlice,
		operator: operatorSlice
	},
})
