import { configureStore } from '@reduxjs/toolkit'
import factorySlice from './slices/factorySlice'
import SimpleFunctionsConfigSlice from './slices/functionConfigSlice'
import ArrayPointsSlice from './slices/pointSlice'

export default configureStore({
	reducer: {
		arrayPoints: ArrayPointsSlice,
		simpleFunctionConfig: SimpleFunctionsConfigSlice,
		factory: factorySlice,
	},
})
