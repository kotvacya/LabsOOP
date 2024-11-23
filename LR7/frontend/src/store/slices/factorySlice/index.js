import instance from '@/utils/axiosInstance'
import { createSlice } from '@reduxjs/toolkit'

let initialState = null

const factorySlice = createSlice({
	name: 'factorySlice',
	initialState,
	reducers: {
		setFactory: (state, action) => {
			instance.post("/tabulated/factory", null, {params: {type: action.payload}})
			return state = action.payload
		}
	},
})

export const { setFactory } = factorySlice.actions
export default factorySlice.reducer
