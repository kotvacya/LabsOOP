import { createSlice } from '@reduxjs/toolkit'

let initialState = null

const factorySlice = createSlice({
	name: 'factorySlice',
	initialState,
	reducers: {
		setFactory: (state, action) => (state = action.payload),
	},
})

export const { setFactory } = factorySlice.actions
export default factorySlice.reducer
