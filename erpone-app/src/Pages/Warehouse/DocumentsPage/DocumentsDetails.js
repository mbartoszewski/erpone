import React, {useState, useContext} from 'react'
import { useParams, Link } from "react-router-dom";
import { makeStyles } from '@material-ui/core/styles'
import { Button, Grid, MenuItem, Paper, TextField, Typography } from '@material-ui/core'
import DocumentsDetailsTable from './DocumentsDetailTable'
import { apiStates, useApi } from '../../../Components/Fetch'
import { globalStateContext } from '../../ErpOneApp';
const useStyles = makeStyles (theme => ({
	root: {
		flexGrow: '1',
		//backgroundColor: 'blue'
		'& .MuiTextField-root': {
      margin: theme.spacing(1),
			minWidth: '20ch',
    },
	},
	paper: {
    padding: theme.spacing(2),
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
    backgroundColor: '#ddf6dd'
	},
	ownerCompanyCard: {
		//backgroundColor: 'green'

	},
	supplierCompanyCard: {
		//backgroundColor: 'yellow'

	},
	docNumber: {
		textAlign: 'center',
		//backgroundColor: 'red'
	},
}))

const WarehouseDocumentsDetails = () =>
{
	const classes = useStyles();
	const { id } = useParams();
	const globalContext = useContext(globalStateContext);
	const currencies = globalContext.dataCurrency;
	const warehouses = globalContext.dataWarehouses;
	const [currency, setCurrency] = useState();
	const [warehouse, setWarehouse] = useState();
	const [paymentTerm, setPaymentTerm] = useState();
	const [paymentForm, setPaymentForm] = useState();
  	const { state, error, data } = useApi(`http://localhost:5000/api/documents/${id}/details`);
  	const fetchedData = React.useMemo(() => data, data);
  	const columns = React.useMemo(() => [
      	{ Header: 'Code', accessor: 'thing.code' },
      	{ Header: 'Name', accessor: 'thing.name' },
      	{ Header: 'Quantity', accessor: 'quantity' },
		{ Header: "Unit", accessor: "thing.unit.code" },
		{ Header: 'Price', accessor: 'price.price' }], []);
	
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
	switch (state)
  {
    case apiStates.ERROR:
      return <p className={classes.errorMsg}>Error: {error} || 'General error'</p>;
    case apiStates.SUCCESS:
      return (
		<div className={classes.root}>
			<div className={classes.documentHeader}>
				<Grid container xs={12} spacing={1}>
					<Grid item xs={4} sm={6} xl={6}>
						<Typography className={classes.docNumber}>
							{fetchedData[0].docNumber}
						</Typography>
					</Grid>
					<Grid item xs={8} sm={6} xl={6}>
						  <Button>
							  Zapisz
						  </Button>
						   <Button>
							  Edytuj
						  </Button>
						   <Button>
							  Usuń
						  </Button>
						  <Button>
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
											variant='outlined'
											label="Contractor name"
											size='small'
											InputProps={{ readOnly: false, }}
											fullWidth={true}>
										</TextField>
						  			</form>
								</Grid>
							  	<Grid item xs={12} sm={12} xl ={6}>
								   <Typography>
										Miasto, ul. Główna 178 {<br></br>}
										99-999 Daleko, {<br></br>}
										Polska {<br></br>}
						  			</Typography>
							 	</Grid>
							   	<Grid item xs={12} sm={12} xl ={6}>
								  	<Typography>
										NIP: 98749486465465465 {<br></br>}
										Regon: 6465466132168684 {<br></br>}
							 		</Typography>
								</Grid>
						  </Grid>  
					</Paper>
				  </Grid>
				<Grid item xs={12} sm={8} xl={6}>
					<Grid container xs={12} spacing={1}>
						<Grid item xs={12} sm={6} xl={6}>
							<Typography >
								Created at: {fetchedData[0].createdAt}
							</Typography>
						</Grid>
						<Grid item xs={12} sm={6} xl={6}>
							<Typography >
								Date to: {fetchedData[0].createdAt}
							</Typography>
						</Grid>
						<Grid item xs={6} sm={6} xl={6}>
							<TextField 
								id='select-to-warehouse'
								variant="outlined"
								select
								disabled
								label="To warehouse"
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
						<Grid item xs={6} sm={6} xl={6}>
							<TextField
								id='select-from-warehouse'
								variant="outlined"
								select
								disabled
								label="From warehouse"
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
						<Grid item xs={6} sm={6} xl={3}>
							<TextField id='select-payment-form'
								variant="outlined"
								select
								label="Payment form"
								value={currency}
								onChange={handleCurrencyChange}
								size="small"
							>
								{currencies.map((option) => (
										<MenuItem key={option.name} value={option.name}>
											{option.code}
										</MenuItem>
									))}	   
							</TextField>
						</Grid>
						<Grid item xs={6} sm={6} xl={3}>
							<TextField id='select-payment-term'
								variant="outlined"
								select
								label="Payment term"
								value={currency}
								onChange={handleCurrencyChange}
								size="small"
								>
								{currencies.map((option) => (
										<MenuItem key={option.name} value={option.name}>
											{option.code}
										</MenuItem>
									))}	   
							</TextField>
						</Grid>
						<Grid item xs={6} sm={6} xl={3}>
								<TextField
									id='select-currency'
									variant="outlined"
									select
									label="Currency"
									value={currency}
									onChange={handleCurrencyChange}
									size="small">
									{currencies.map((option) => (
										<MenuItem key={option.name} value={option.name}>
											{option.code}
										</MenuItem>
									))}	  
								</TextField>
						</Grid>
						<Grid item xs={6} sm={6} xl={3}>
							<Typography >
								Status: {fetchedData[0].statusTypeEnum}
							</Typography>
						</Grid>
					</Grid>
				  </Grid>	
			</Grid>	
			</div>
			<div>
				<DocumentsDetailsTable
					data={fetchedData[0].documentDetails}
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
export default WarehouseDocumentsDetails;