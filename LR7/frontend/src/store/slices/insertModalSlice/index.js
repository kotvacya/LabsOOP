import { createSlice } from '@reduxjs/toolkit'

let initialState = { visible: false, id: null }

const insertSlice = createSlice({
	name: 'insertSlice',
	initialState,
	reducers: {
		setInsertVisibility: (state, action) => {
			state.visible = action.payload.visible
			state.id = action.payload.id
			console.log('vis', state)
		},
	},
})

export const { setInsertVisibility, setInsertValues } = insertSlice.actions
export default insertSlice.reducer
