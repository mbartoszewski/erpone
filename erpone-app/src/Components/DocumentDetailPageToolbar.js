import React from "react";
import { Button, ButtonGroup, Grid, Stack, Typography } from "@mui/material";
import {makeStyles} from "@mui/styles";
import clsx from 'clsx';
import SaveIcon from '@mui/icons-material/Save';
import DeleteForeverIcon from '@mui/icons-material/DeleteForever';
import EditIcon from '@mui/icons-material/Edit';
import CloseIcon from '@mui/icons-material/Close';
import PrintIcon from '@mui/icons-material/Print';
import DropDownMenu from "./DropDownMenu";
import ImportExportIcon from '@mui/icons-material/ImportExport';

const useStyles = makeStyles(theme => ({
docNumber: {
		fontSize: '1.3em'
	},
	saveButtonHidden: {
	display: "none"
	},
	editButtonHidden: {
	display: "none"
	},
	cancelEditButtonHidden: {
	display: "none"
	},
	deleteButtonHidden: {
	display: "none"
	},
	printButtonHidden: {
	display: "none"
	},
	exportButtonHidden: {
	display: "none"
	},
}));
const options = ['to Excel', 'to PDF'];

const DocumentDetailPageToolbar = (props) =>
{
	const classes = useStyles();

	const handleEditButtonClick = () =>
	{
		props.setIsEdit(true)
	}
	const handleSaveButtonClick = () =>
	{
		props.setIsEdit(false)
	}
	const handleCancelButtonClick = () =>
	{
		props.setIsEdit(false)
		}
	return (
		 <Grid container spacing={1} >
			<Grid container item xs={12} md={6} xl={6} justifyContent="center">
				<Typography variant="h6">
				{props.title !== null ? props.title : ""} {props.docNumber}
				</Typography>
			</Grid>
			<Grid container item xs={12} md={6} xl={6} justifyContent="flex-end">
			<Stack direction="row" spacing={1}>
					<Button
						color='inherit'
						startIcon={<SaveIcon />}
						className={clsx(classes.saveButton, !props.isEditProp && classes.saveButtonHidden)}
						onClick={handleSaveButtonClick}>
								Save
						</Button>
					<Button
						color='inherit'
						startIcon={<EditIcon />}
						className={clsx(classes.editButton, props.isEditProp && classes.editButtonHidden)}
						onClick={handleEditButtonClick}>
								Edit
						</Button>
						<Button
						color='inherit'
						startIcon={<CloseIcon />}
						className={clsx(classes.cancelEditButton, !props.isEditProp && classes.cancelEditButtonHidden)}
						onClick={handleCancelButtonClick}>
								Cancel
						</Button>
						<Button
						color='inherit'
						startIcon={<DeleteForeverIcon />}
						className={clsx(classes.deleteButton, props.isEditProp && classes.deleteButtonHidden)}>
								Delete
						</Button>
					<Button
						color='inherit'
						startIcon={<PrintIcon />}
						className={clsx(classes.printButton, props.isEditProp && classes.printButtonHidden)}>
								Print
					</Button>
					<DropDownMenu
						hidden={props.isEditProp === true ? true : false}
						buttonTitle={"Export"}
						icon={<ImportExportIcon />}
						menuOptions={options} />
				</Stack>
			</Grid> 
     	 </Grid>
	);
}
export default DocumentDetailPageToolbar;