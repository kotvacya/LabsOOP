import { configureStore } from '@reduxjs/toolkit'
import ArrayPointsSlice from './slices/pointsSlice'

export default configureStore({
	reducer: {
		arrayPoints: ArrayPointsSlice,
	},
})
