import instance from '@/utils/axiosInstance'
import { createAsyncThunk, createSlice } from '@reduxjs/toolkit'

let initialState = {copyto: null, functions: []}

export const fetchAllOperands = createAsyncThunk(
	"operandSlice/fetchAll",
	async (thunkApi) => {
		const response = await instance.get("/tabulated/operands")
		return response.data
	}
)

export const fetchOperand = createAsyncThunk(
	"operandSlice/fetchOne",
	async (action, thunkApi) => {
		const response = await instance.get(`/tabulated/operands/${action}`)
		return {data: response.data, id: action}
	}
)

export const operandSetY = createAsyncThunk(
	"operandSlice/setY",
	async (action, thunkApi) => {
		const response = await instance.post(`/tabulated/operands/${action.func_id}/setY`, null, {
            params: {
                index: action.index,
                y: action.y
            }
        })
		return {data: response.data, id: func_id}
	}
)

export const operandRemove = createAsyncThunk(
	"operandSlice/remove",
	async (action, thunkApi) => {
		const response = await instance.post(`/tabulated/operands/${action.func_id}/remove`, null, {
            params: {
                index: action.index,
            }
        })
		return {data: response.data, id: func_id}
	}
)

export const operandInsert = createAsyncThunk(
	"operandSlice/insert",
	async (action, thunkApi) => {
		const response = await instance.post(`/tabulated/operands/${action.func_id}/setY`, null, {
            params: {
                x: action.x,
                y: action.y
            }
        })
		return {data: response.data, id: func_id}
	}
)

function setOperand(state, action){
    const points = action.payload.data.points
    state.functions[action.payload.id] = {points: points.map((v,i)=>({ id: i++, x: v.x, y: v.y })), ...action.payload.data}
}

const operandSlice = createSlice({
	name: 'operandSlice',
	initialState,
	reducers: {
		setCopyToOperator: (state, action) => {
			state.copyto = action.payload
		}
	},
	extraReducers: (builder) => {
		builder
		.addCase(fetchAllOperands.fulfilled, (state, action) => {
            state.functions = action.payload.map(func => {
                if(!func.points) return func
                const points = func.points
                return {points: points.map((v,i)=>({ id: i++, x: v.x, y: v.y })), ...action.payload.data}
            })
        })
        .addCase(fetchOperand.fulfilled, setOperand)
        .addCase(operandSetY.fulfilled, setOperand)
        .addCase(operandInsert.fulfilled, setOperand)
        .addCase(operandRemove.fulfilled, setOperand)
	}
})

export const { setCopyToOperator } = operandSlice.actions
export default operandSlice.reducer
