import instance from '@/utils/axiosInstance'
import { mapFunction } from '@/utils/functionUtils'
import { createAsyncThunk, createSlice } from '@reduxjs/toolkit'

let initialState = { id: 0, points: [] }

export const fetchCurrentFunction = createAsyncThunk('ArrayPointsSlice/fetch', async (thunkApi) => {
	const response = await instance.get('/tabulated')
	return response.data
})

const ArrayPointsSlice = createSlice({
	name: 'arrayPoints',
	initialState,
	reducers: {
		addPoint: (state, action) => {
			state.points.push({ id: state.id, x: null, y: null })
			state.id += 1
		},

		removePoint: (state, action) => {
			state.points = state.points.filter((point) => point.id !== action.payload)
		},

		updatePoint: (state, action) => {
			const { id, x, y } = action.payload
			const idx = state.points.findIndex((point) => point.id === id)
			if (idx !== -1) {
				if (x !== undefined) state.points[idx].x = x
				if (y !== undefined) state.points[idx].y = y
			}
		},

		setPointCount: (state, action) => {
			try {
				let count = action.payload
				if (count > 2000) count = 2000

				const diff = Math.max(0, count - state.points.length)

				state.points = [
					...state.points.slice(0, count),
					...Array(diff)
						.fill()
						.map((v, i) => ({ id: state.id++, x: null, y: null })),
				]
			} catch (error) {}
		},
	},

	extraReducers: (builder) => {
		builder.addCase(fetchCurrentFunction.fulfilled, (state, action) => {
			state.points = mapFunction(action.payload, state.id)?.points || []
			state.id += state.points.length
		})
	},
})

export const { addPoint, removePoint, updatePoint, setPointCount } = ArrayPointsSlice.actions
export default ArrayPointsSlice.reducer
