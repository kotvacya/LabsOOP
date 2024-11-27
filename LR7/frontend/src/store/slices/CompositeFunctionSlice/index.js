import { createSlice } from '@reduxjs/toolkit'

let initialState = { config: { name: null, outer: null, inner: null} }

const CompositeFunctionSlice = createSlice({
	name: 'compositeFunctionConfig',
	initialState,
	reducers: {
		setName: (state, action) => {
			state.config.name = action.payload
		},
		setOuter: (state, action) => {
			state.config.outer = action.payload
		},
		setInner: (state, action) => {
			state.config.inner = action.payload
		},
	}
})

export const { setName, setInner, setOuter } = CompositeFunctionSlice.actions
export default CompositeFunctionSlice.reducer
