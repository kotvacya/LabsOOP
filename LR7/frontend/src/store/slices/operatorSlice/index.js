import instance from '@/utils/axiosInstance'
import { createAsyncThunk, createSlice } from '@reduxjs/toolkit'

let initialState = {current: null, all: []}

export const fetchAllOperators = createAsyncThunk(
	"operatorSlice/fetchAll",
	async (thunkApi) => {
		const response = await instance.get("/tabulated/operands/methods")
		return response.data.map(func => ({text: func.symbol, value: func.name}))
	}
)

const operatorSlice = createSlice({
	name: 'operatorSlice',
	initialState,
	reducers: {
		setOperator: (state, action) => {
			state.current = action.payload
		}
	},
	extraReducers: (builder) => {
		builder
		.addCase(fetchAllOperators.fulfilled, (state, action)=> {
			state.all = action.payload
		})
	}
})

export const { setOperator } = operatorSlice.actions
export default operatorSlice.reducer
