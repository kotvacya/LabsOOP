import { createSlice } from '@reduxjs/toolkit'

let initialState = {function: null, start: null, end: null, count: null}

const SimpleFunctionsConfigSlice = createSlice({
	name: 'simpleFunctionConfig',
	initialState,
	reducers: {
		setFunction: (state, action) => {
			state.function = action.payload;
		},
        setStart: (state, action) => {
			state.start = action.payload;
		},
        setEnd: (state, action) => {
			state.end = action.payload;
		},
        setCount: (state, action) => {
			state.count = action.payload;
		},
	},
})

export const { setFunction, setStart, setEnd, setCount } = SimpleFunctionsConfigSlice.actions
export default SimpleFunctionsConfigSlice.reducer
