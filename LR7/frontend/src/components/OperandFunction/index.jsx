'use client'
import classNames from '@/utils/classNames'
import ArrayFunction from '../ArrayFunction'
import CreateButton from './Buttons/CreateButton'
import SaveButton from './Buttons/SaveButton'
import UploadButton from './Buttons/UploadButton'
import styles from './index.module.css'

export default ({ id, immutable, disabled, ...rest }) => {
	const onCreate = () => {}
	const onSave = () => {}
	const onUpload = () => {}

	return (
		<div className={classNames(styles.wrapper, disabled && styles.disabled)}>
			<div className={styles.buttons}>
				{!immutable && <CreateButton onClick={onCreate} />}
				<SaveButton onClick={onSave} />
				{!immutable && <UploadButton onClick={onUpload} />}
			</div>
			<ArrayFunction {...rest} />
		</div>
	)
}
