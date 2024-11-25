
export function mapFunction(func){
    if(!func) return {points: []}
    const points = func.points
    return {...func, points: points?.map((v,i)=>({ id: i++, x: v.x, y: v.y }))}
}
