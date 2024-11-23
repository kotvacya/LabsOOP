import { configureStore } from '@reduxjs/toolkit'
import ArrayPointsSlice from './slices/pointsSlice'
import SimpleFunctionsConfigSlice from './slices/functionConfigSlice'

export default configureStore({
	reducer: {
		arrayPoints: ArrayPointsSlice,
		simpleFunctionConfig: SimpleFunctionsConfigSlice
	},
})
