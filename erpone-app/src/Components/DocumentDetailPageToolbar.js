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
import { docStates } from './Helpers';
import { useParams, useHistory} from "react-router-dom";
import { useApii } from "./Fetch";

const useStyles = makeStyles(theme => ({
docNumber: {
		fontSize: '1.3em'
	},
	saveButtonHidden: {
	display: "none"
	},
	saveButtonShow: {
	display: "-webkit-inline-flex"
	},
	editButtonHidden: {
	display: "none"
	},
	cancelEditButtonHidden: {
	display: "none"
	},
	cancelEditButtonShow: {
	display: "-webkit-inline-flex"
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
const options = [{title: 'Excel', function: undefined}, {title: 'PDF', function: undefined}];

const DocumentDetailPageToolbar = (props) =>
{
	const classes = useStyles();
	const { id } = useParams();
	const [{response}, doFetch] = useApii(null, null)
	const handleEditButtonClick = () =>
	{
		props.setDocState(docStates.EDIT)
	}
	const handleSaveButtonClick = () =>
	{
		props.setDocState(docStates.VIEW)
		props.setOriginalDoc(props.doc)
	
		doFetch({ url: `http://localhost:5000/api/documents/${id}/`, options: {
			method: "PUT",
			headers: {
				"Content-Type": "application/json"
			},
			body: JSON.stringify(props.doc)
			}})
	}
	const handleCancelButtonClick = () =>
	{
		
		if (props.docStateProp !== docStates.ADD)
		{
			props.setDoc(props.originalDoc)
			props.setDocState(docStates.VIEW)
		} 
		else
		{
			props.setDocState(docStates.VIEW)
		}
		
	}
	const handleDeleteButtonClick = async e =>
	{
		if (id != undefined && id != null)
		{
			doFetch({ url: `http://localhost:5000/api/documents/${id}/`, options: {
			method: "DELETE",
			headers: {
				"Content-Type": "application/json"
			},
			}})
		}
	}
	return (
		<Grid container spacing={1}>
			<Grid  item xs={6} md={3} xl={3} justifyContent="flex-start" >
				<Typography variant="h6">
				{props.docNumber != null ? props.docNumber : ""}
				</Typography>
			</Grid>
			<Grid  item xs={6} md={3} xl={3} justifyContent="center" >
				<Typography variant="h6">
				{props.selected != null ? "selected: " : "x"}
				</Typography>
			</Grid>
			<Grid item xs={12} md={6} xl={6} justifyContent="flex-end">
			<Stack direction="row" spacing={1}>
					<Button
						color='inherit'
						startIcon={<SaveIcon />}
						className={clsx(classes.saveButtonHidden, (props.docStateProp === docStates.EDIT || props.docStateProp === docStates.ADD) && classes.saveButtonShow)}
						onClick={handleSaveButtonClick}>
						Save
						</Button>
					<Button
						color='inherit'
						startIcon={<EditIcon />}
						className={clsx(classes.editButton, (props.docStateProp === docStates.EDIT || props.docStateProp === docStates.ADD) && classes.editButtonHidden)}
						onClick={handleEditButtonClick}>
								Edit
						</Button>
						<Button
						color='inherit'
						startIcon={<CloseIcon />}
						className={clsx(classes.cancelEditButtonHidden, (props.docStateProp === docStates.EDIT || props.docStateProp === docStates.ADD) && classes.cancelEditButtonShow)}
						onClick={handleCancelButtonClick}>
								Cancel
						</Button>
						<Button
						color='inherit'
						startIcon={<DeleteForeverIcon />}
						className={clsx(classes.deleteButton, (props.docStateProp === docStates.EDIT || props.docStateProp === docStates.ADD) && classes.deleteButtonHidden)}
						onClick={handleDeleteButtonClick}>
								Delete
						</Button>
					<Button
						color='inherit'
						startIcon={<PrintIcon />}
						className={clsx(classes.printButton, (props.docStateProp === docStates.EDIT || props.docStateProp === docStates.ADD) && classes.printButtonHidden)}>
								Print
					</Button>
					<DropDownMenu
						hidden={(props.docStateProp === docStates.EDIT || props.docStateProp === docStates.ADD) ? true : false}
						buttonTitle={"Export"}
						icon={<ImportExportIcon />}
						menuOptions={options} />
				</Stack>
			</Grid> 
     	 </Grid>
	);
}
export default DocumentDetailPageToolbar;