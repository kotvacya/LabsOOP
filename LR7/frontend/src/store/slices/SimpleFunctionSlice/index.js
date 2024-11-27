import instance from '@/utils/axiosInstance'
import { createAsyncThunk, createSlice } from '@reduxjs/toolkit'

let initialState = { config: { function: null, start: null, end: null, count: null }, functions: [] }

export const fetchSimpleFunctions = createAsyncThunk(
	"simpleFunctions/fetchAll",
	async (thunkApi) => {
		const response = await instance.get("/tabulated/simple")
		return response.data.map(func => ({text: func.canonical_name, value: func.type}))
	}
)

const SimpleFunctionsConfigSlice = createSlice({
	name: 'simpleFunctionConfig',
	initialState,
	reducers: {
		setFunction: (state, action) => {
			state.config.function = action.payload
		},
		setStart: (state, action) => {
			state.config.start = action.payload
		},
		setEnd: (state, action) => {
			state.config.end = action.payload
		},
		setCount: (state, action) => {
			state.config.count = action.payload
		},
	},
	extraReducers: (builder) => {
		builder
		.addCase(fetchSimpleFunctions.fulfilled, (state, action)=> {
			state.functions = action.payload
		})
	}
})

export const { setFunction, setStart, setEnd, setCount } = SimpleFunctionsConfigSlice.actions
export default SimpleFunctionsConfigSlice.reducer
