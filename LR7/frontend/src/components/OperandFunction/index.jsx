'use client'
import ArrayFunction from '../ArrayFunction'
import CreateButton from './Buttons/CreateButton'
import SaveButton from './Buttons/SaveButton'
import UploadButton from './Buttons/UploadButton'
import styles from './index.module.css'

export default ({ id, immutable, ...rest }) => {
	const onCreate = () => {}
	const onSave = () => {}
	const onUpload = () => {}

	return (
		<div className={styles.wrapper}>
			<div className={styles.buttons}>
				{!immutable && <CreateButton onClick={onCreate} />}
				<SaveButton onClick={onSave} />
				{!immutable && <UploadButton onClick={onUpload} />}
			</div>
			<ArrayFunction {...rest} />
		</div>
	)
}
