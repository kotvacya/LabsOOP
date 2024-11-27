import instance from '@/utils/axiosInstance'
import { createAsyncThunk, createSlice } from '@reduxjs/toolkit'

let initialState = {current: null, all: []}

export const fetchAllFactories = createAsyncThunk(
	"factorySlice/fetchAll",
	async (thunkApi) => {
		const response = await instance.get("/tabulated/factory/all")
		return response.data.map(func => ({text: func.canonical_name, value: func.type}))
	}
)

export const fetchCurrentFactory = createAsyncThunk(
	"factorySlice/fetchCurrent",
	async (thunkApi) => {
		const response = await instance.get("/tabulated/factory")
		return response.data.type
	}
)

const factorySlice = createSlice({
	name: 'factorySlice',
	initialState,
	reducers: {
		setFactory: (state, action) => {
			instance.post("/tabulated/factory", null, {params: {type: action.payload}})
			state.current = action.payload
		}
	},
	extraReducers: (builder) => {
		builder
		.addCase(fetchAllFactories.fulfilled, (state, action)=> {
			state.all = action.payload
		})
		.addCase(fetchCurrentFactory.fulfilled, (state, action) => {
			state.current = action.payload
		})
	}
})

export const { setFactory } = factorySlice.actions
export default factorySlice.reducer
