import React, {useState, useContext} from 'react'
import { useParams, Link } from "react-router-dom";
import { makeStyles } from '@material-ui/core/styles'
import { Button, Grid, MenuItem, Paper, TextField, Typography } from '@material-ui/core'
import DocumentsDetailsTable from './DocumentsDetailsTable'
import { apiStates, useApi } from './Fetch'
import { globalStateContext } from '../Pages/ErpOneApp';

const useStyles = makeStyles (theme => ({
	root: {
		flexGrow: '1',
		'& .MuiTextField-root': {
      margin: theme.spacing(1),
			minWidth: '20ch',
    },
	},
	paper: {
    padding: theme.spacing(1),
    color: theme.palette.text.secondary,
	},
		errorMsg: {
	position: 'absolute',
	top: '50%',
	left: '50%'
	},
	documentHeader: {
		position: 'sticky',
		top: '0',
		padding: '0.3em',
		border: '1px solid grey',
		backgroundColor: 'white',
		display: "flex",
		justifyContent: "flex-end"
	},
	docNumber: {
		textAlign: 'center',
		//backgroundColor: 'red'
	},
}))

const DocumentsDetailsPage = () =>
{
	const classes = useStyles();
	const { id } = useParams();
	const globalContext = useContext(globalStateContext);
	const currencies = globalContext.dataCurrencies;
	const warehouses = globalContext.dataWarehouses;
	const paymentForms = globalContext.dataPaymentForms;
	const paymentTerms = globalContext.dataPaymentTerms;
	const docStatus = globalContext.dataDocumentStatus;
	const [currency, setCurrency] = useState();
	const [warehouse, setWarehouse] = useState();
	const [paymentTerm, setPaymentTerm] = useState();
	const [paymentForm, setPaymentForm] = useState();
	const [documentStatus, setDocumentStatus] = useState();
  	const { state: documentDetailState, error: documentDetailError, data: documentDetailData } = useApi(`http://localhost:5000/api/documents/${id}/details`);
  	const fetchedData = React.useMemo(() => documentDetailData, documentDetailData);
	const columns = React.useMemo(() => [
		{ Header: 'Code', accessor: 'thing.code' },
		{ Header: 'Name', accessor: 'thing.name' },
		{ Header: "Unit", accessor: "thing.unit.code" },
		{
			Header: 'Quantity', accessor: 'quantity', Footer: info =>
			{
				const totalQuantity = React.useMemo(
				() => info.rows.reduce((sum, row) => row.values.quantity + sum, 0),[info.rows]
				)
				return <>Total quantity: {totalQuantity}</>
		} },
		{ Header: 'Net price', accessor: 'detailPrice'},
		{ Header: 'Net', accessor: 'net',  accessor: row =>
		{
			return <>{(row.quantity) * (row.detailPrice)}</>
		}, Footer: info =>
			{
			const totalNetValue = React.useMemo(
				() => info.rows.reduce((sum, row) => row.values.Net.props.children + sum, 0),[info.rows]
				)
				return <>Total net value: {totalNetValue}</>
			}
		}], []);

	const handleCurrencyChange = (event) =>
	{
		setCurrency(event.target.value);
	}
	const handleWarehouseChange = (event) =>
	{
		setWarehouse(event.target.value);
	}
	const handlePaymentFormChange = (event) =>
	{
		setPaymentForm(event.target.value);
	}
	const handlePaymentTermChange = (event) =>
	{
		setPaymentTerm(event.target.value);
	}
	const handleDocumentStatusChange = (event) =>
	{
		setDocumentStatus(event.target.value);
	}
	switch (documentDetailState)
  {
	case apiStates.ERROR:
	case apiStates.EMPTY:
      return <p className={classes.errorMsg}>Error: {documentDetailError} || 'General error'</p>;
    case apiStates.SUCCESS:
      return (
		<div className={classes.root}>
			<div className={classes.documentHeader}>
				<Grid container xs={12} spacing={1}>	  
					<Grid item xs={4} sm={6} xl={8}>
					<Typography className={classes.docNumber}>
							  {fetchedData.docNumber}
					</Typography>
					</Grid>
					<Grid item xs={8} sm={6} xl={4}>
						<Button variant='outlined'>
							Zapisz
						</Button>
						<Button variant='outlined'>
							Edytuj
						</Button>
						<Button variant='outlined'>
							Usuń
						</Button>
						<Button variant='outlined'>
							PDF
						</Button>
					</Grid>				 
					<Grid item xs={12} sm={4} xl={6}>
					  <Paper className={classes.paper}>
						  <Grid container xs={12} spacing={1}>
							   <Grid item xs={12} sm={12} xl ={12}>
								   <form>
										<TextField
											id='textfield-contractor-name'
											Disa
											variant='outlined'
											label="Contractor name"
											size='small'
											InputProps={{ readOnly: true, }}
											fullWidth={true}
											defaultValue={fetchedData.contractor.name != null ? fetchedData.contractor.name: ""}>
										</TextField>
						  			</form>
								</Grid>
							  	<Grid item xs={12} sm={12} xl ={6}>
								   <Typography>
										{fetchedData.contractor.address.street != null ? fetchedData.contractor.address.street + " ": ""}
										{fetchedData.contractor.address.number !=null? fetchedData.contractor.address.number + ", ": ""}{<br></br>}
										{fetchedData.contractor.address.postalCode != null ? fetchedData.contractor.address.postalCode + " " : ""}
										{fetchedData.contractor.address.city !=null? fetchedData.contractor.address.city + ", ": ""}{<br></br>}
										{fetchedData.contractor.address.country !=null? fetchedData.contractor.address.country: ""}{<br></br>}
						  			</Typography>
							 	</Grid>
							   	<Grid item xs={12} sm={12} xl ={6}>
								  	<Typography>
										NIP: {fetchedData.contractor.nip != null ? fetchedData.contractor.nip: ""} {<br></br>}
										Regon: {fetchedData.contractor.regon != null ? fetchedData.contractor.regon: ""} {<br></br>}
							 		</Typography>
								</Grid>
						  </Grid>  
					</Paper>
				  </Grid>
				<Grid item xs={12} sm={8} xl={6}>
					<Grid container xs={12} spacing={1}>
						<Grid item xs={12} sm={6} xl={6}>
							<TextField
								type="datetime-local"
									  label="Created date:"
									  InputProps={{ readOnly: true, }}
								defaultValue = {fetchedData.createdAt != null ? fetchedData.createdAt : ""}>
							</TextField>
						</Grid>
						<Grid item xs={12} sm={6} xl={6}>
							<TextField
								type="datetime-local"
									  label="Requested date:"
									  InputProps={{ readOnly: true, }}
								defaultValue = {fetchedData.targetDateTime != null ? fetchedData.targetDateTime : ""}>
							</TextField>
						</Grid>
						<Grid item xs={6} sm={6} xl={4}>
							<TextField id='select-payment-form'
								variant="outlined"
								select
								label="Payment form"
								InputProps={{ readOnly: true, }}
								value={paymentForm}
								onChange={handlePaymentFormChange}
								size="small"
							>
								{paymentForms.map((option) => (
										<MenuItem key={option.code} value={option.name}>
											{option.name}
										</MenuItem>
									))}	   
							</TextField>
						</Grid>
						<Grid item xs={6} sm={6} xl={4}>
							<TextField id='select-payment-term'
								variant="outlined"
								select
								label="Payment term"
								InputProps={{ readOnly: true, }}
								value={paymentTerm}
								onChange={handlePaymentTermChange}
								size="small"
								>
								{paymentTerms.map((option) => (
										<MenuItem key={option.lenght} value={option.name}>
											{option.name}
										</MenuItem>
									))}	   
							</TextField>
						</Grid>
						<Grid item xs={6} sm={6} xl={4}>
								<TextField
									id='select-currency'
									variant="outlined"
									select
									label="Currency"
									InputProps={{ readOnly: true, }}
									value={currency}
									onChange={handleCurrencyChange}
									size="small">
									{currencies.map((option) => (
										<MenuItem key={option.code} value={option.code}>
											{option.code}
										</MenuItem>
									))}	  
								</TextField>
						</Grid>
						<Grid item xs={6} sm={6} xl={4}>
							<TextField 
								id='select-to-warehouse'
								variant="outlined"
								select
								label="To warehouse"
								InputProps={{ readOnly: true, }}
								value={warehouse}
								onChange={handleWarehouseChange}
								size="small">
									{warehouses.map((option) => (
										<MenuItem key={option.name} value={option.name}>
											{option.code}
										</MenuItem>
									))}	  
							</TextField>
						</Grid>
						<Grid item xs={6} sm={6} xl={4}>
							<TextField
								id='select-from-warehouse'
								variant="outlined"
								select
								label="From warehouse"
								InputProps={{ readOnly: true, }}
								value={warehouse}
								onChange={handleWarehouseChange}
								size="small">
									{warehouses.map((option) => (
										<MenuItem key={option.name} value={option.name}>
											{option.code}
										</MenuItem>
									))}	  
							</TextField>
						</Grid>
						<Grid item xs={6} sm={6} xl={4}>
							<TextField
								id='select-document-status'
								variant="outlined"
								select
								label="Status"
								disabled
								value={warehouse}
								onChange={handleWarehouseChange}
								size="small">
									{warehouses.map((option) => (
										<MenuItem key={option.name} value={option.name}>
											{option.code}
										</MenuItem>
									))}	  
							</TextField>
						</Grid>
					</Grid>
				  </Grid>	
			</Grid>	
			</div>
			<div>
				<DocumentsDetailsTable
					data={fetchedData.documentDetails}
					columns={columns}
					manual
        		/>
			</div>
		</div>
      );
    default:
      return <p className={classes.errorMsg}>Loading....</p>;
  }
}
export default DocumentsDetailsPage;