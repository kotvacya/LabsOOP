import { createSlice } from '@reduxjs/toolkit'

// popup = { id: , success: , message: }
let id = 0
let initialState = []

const modalSlice = createSlice({
	name: 'modalSlice',
	initialState,
	reducers: {
		// payload = { success: , message: }
		createPopup: (state, action) => {
			state.push({ id: ++id, ...action.payload })
		},

		// payload = id
		deletePopup: (state, action) => state.filter((el) => el.id != action.payload),
	},
})

// payload = { success: , message: , duration: }
export const createTimeLimitedPopup = (payload) => (dispatch) => {
	dispatch(createPopup({ success: payload.success, message: payload.message }))
	const current_id = id
	setTimeout(() => dispatch(deletePopup(current_id)), payload.duration * 1000)
}

export const { createPopup, deletePopup } = modalSlice.actions
export default modalSlice.reducer
