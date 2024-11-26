'use client'
import { fetchCurrentFunction } from '@/store/slices/pointSlice'
import instance from '@/utils/axiosInstance'
import { useRouter } from 'next/navigation'
import ArrayFunction from '../ArrayFunction'
import CreateButton from './Buttons/CreateButton'
import SaveButton from './Buttons/SaveButton'
import UploadButton from './Buttons/UploadButton'
import styles from './index.module.css'
import { useDispatch } from 'react-redux'
import { setOperand } from '@/store/slices/operandSlice'

export default ({ id, immutable, disabled, ...rest }) => {
	const router = useRouter()
	const dispatch = useDispatch()

	async function onCreate(e) {
		await instance.post("/tabulated/operands/get", null, {
			params: {index: id} 
		})
		await dispatch(fetchCurrentFunction()).unwrap()
		router.push(`/?copy_to=${id}`)
	}

	const opts = {
		types: [{
			description: "Tabulated Function",
			accept: { "application/x-binary": [".bin"] },
		}],
	};

	async function onSave(e) {
		try{
			const handle = await window.showSaveFilePicker(opts);
			
			const response = await instance.get(`/tabulated/operands/${id}/serialized`, {
				responseType: 'blob'
			})

			const writableStream = await handle.createWritable();
			await writableStream.write(response.data);
			await writableStream.close();
		}catch(e){
			if(e.name !== "AbortError") throw e
		}
	}

	async function onUpload(e) {
		try{
			const [handle] = await window.showOpenFilePicker(opts);

			const fileData = await handle.getFile();

			const response = await instance.post(`/tabulated/operands/${id}/serialized`, await fileData.arrayBuffer(), {
				headers: {
					'Content-Type': 'application/octet-stream'
				}
			})

			dispatch(setOperand({id: id, data: response.data}))
		}catch(e){
			if(e.name !== "AbortError") throw e
		}
	}

	async function onChangeY(pt, y) {
		console.log(y);
	}

	return (
		<div className={styles.wrapper}>
			<div className={styles.buttons}>
				{!immutable && <CreateButton onClick={onCreate} />}
				<SaveButton onClick={onSave} />
				{!immutable && <UploadButton onClick={onUpload} />}
			</div>
			<ArrayFunction onChangeY={onChangeY} {...rest} />
		</div>
	)
}
