import { createSlice } from '@reduxjs/toolkit'

let initialState = {id: 0, points: []}

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
			if(idx !== -1){
				if(x !== undefined) state.points[idx].x = x
				if(y !== undefined) state.points[idx].y = y
			}
		},

		setPointCount: (state, action) => {
			try {
				const count = action.payload;
				const diff = Math.max(0, count - state.points.length)
				
				state.points = [ ...state.points.slice(0, count), ...Array(diff).fill().map((v,i)=>({ id: state.id++, x: null, y: null })) ]
			} catch (error) {
				
			}
			
		}
	},
})

export const { addPoint, removePoint, updatePoint, setPointCount } = ArrayPointsSlice.actions
export default ArrayPointsSlice.reducer
