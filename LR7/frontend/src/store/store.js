import { configureStore } from '@reduxjs/toolkit'
import ArrayDotsSlice from './ArrayDotsSlice'

export default configureStore({
	reducer: {
		arrayDots: ArrayDotsSlice,
	},
})
