import instance from '@/utils/axiosInstance'
import { mapFunction } from '@/utils/functionUtils'
import { createAsyncThunk, createSlice } from '@reduxjs/toolkit'

let initialState = { functions: [], uid: 0 }

export const fetchAllOperands = createAsyncThunk('operandSlice/fetchAll', async (thunkApi) => {
	const response = await instance.get('/tabulated/operands')
	return response.data
})

export const fetchOperand = createAsyncThunk('operandSlice/fetchOne', async (action, thunkApi) => {
	const response = await instance.get(`/tabulated/operands/${action}`)
	return { data: response.data, id: action }
})

export const operandSetY = createAsyncThunk('operandSlice/setY', async (action, thunkApi) => {
	const response = await instance.post(`/tabulated/operands/${action.func_id}/setY`, null, {
		params: {
			index: action.index,
			y: action.y,
		},
	})
	return { data: response.data, id: action.func_id }
})

export const operandRemove = createAsyncThunk('operandSlice/remove', async (action, thunkApi) => {
	const response = await instance.post(`/tabulated/operands/${action.func_id}/remove`, null, {
		params: {
			index: action.index,
		},
	})
	return { data: response.data, id: action.func_id }
})

export const operandInsert = createAsyncThunk('operandSlice/insert', async (action, thunkApi) => {
	const response = await instance.post(`/tabulated/operands/${action.func_id}/insert`, null, {
		params: {
			x: action.x,
			y: action.y,
		},
	})
	return { data: response.data, id: action.func_id }
})

function setOperandInner(state, action) {
	const func = mapFunction(action.payload.data, state.uid)
	state.functions[action.payload.id] = func
	state.uid += func.points.length
}

const operandSlice = createSlice({
	name: 'operandSlice',
	initialState,
	reducers: {
		setOperand: setOperandInner,
	},
	extraReducers: (builder) => {
		builder
			.addCase(fetchAllOperands.fulfilled, (state, action) => {
				state.functions = action.payload.map(mapFunction)
			})
			.addCase(fetchOperand.fulfilled, setOperandInner)
			.addCase(operandSetY.fulfilled, setOperandInner)
			.addCase(operandInsert.fulfilled, setOperandInner)
			.addCase(operandRemove.fulfilled, setOperandInner)
	},
})

export const { setOperand } = operandSlice.actions
export default operandSlice.reducer
