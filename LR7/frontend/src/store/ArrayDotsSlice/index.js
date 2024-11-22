import { createSlice } from '@reduxjs/toolkit'

let initialState = [{ id: Date.now(), x: '', y: '' }]

const arrayDotsSlice = createSlice({
	name: 'arrayDots',
	initialState,
	reducers: {
		addDot: (state, action) => {
			state.push({ id: action.payload.id, x: action.payload.x, y: action.payload.y })
		},

		removeDot: (state, action) => {
			state = state.filter((dot) => dot.id !== action.payload)
		},

		updateDot: (state, action) => {
			const { id, value } = action.payload
			const input = state.inputs.find((input) => input.id === id)
			if (input) input.value = value
		},
	},
})

export const { addDot, removeDot, updateDot } = arrayDotsSlice.actions
export default arrayDotsSlice.reducer
