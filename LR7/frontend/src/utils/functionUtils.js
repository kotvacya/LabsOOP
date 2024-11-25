
export function mapFunction(func, current_id){
    if(!func) return {points: []}
    const points = func.points
    return {...func, points: points?.map((v,i)=>({ id: (current_id || 0) + i++, x: v.x, y: v.y }))}
}
