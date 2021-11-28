import React, {useState, useContext, useEffect} from 'react'
import { Link, useParams} from "react-router-dom";
import { Grid, MenuItem, TextField, Typography } from '@mui/material'
import { styled, } from '@mui/material/styles';
import DocumentsDetailsTable from './DocumentsDetailsTable'
import { apiStates, useApi } from './Fetch'
import { globalStateContext } from '../Pages/ErpOneApp';
import ReactDOM from 'react-dom';
import DocumentDetailPageToolbar from './DocumentDetailPageToolbar';
import Autocomplete from '@mui/material/Autocomplete';

const root = (theme) => ({
		flexGrow: '1',
		'& .MuiTextField-root': {
      margin: theme.spacing(1),
			minWidth: '20ch',
    },
})

const DocumentHeader = styled('div')(({ theme }) => ({
  position: 'sticky',
		top: '0px',
		padding: '0.3em',
		borderBottom: `4px solid ${theme.palette.divider}`,
		backgroundColor: 'white',
		display: "flex",
		justifyContent: "flex-end"
}));

const DocumentsDetailsPage = (props) =>
{
	const { id } = useParams();
	const globalContext = useContext(globalStateContext);
	const currencies = globalContext.dataCurrencies;
	const warehouses = globalContext.dataWarehouses;
	const paymentForms = globalContext.dataPaymentForms;
	const paymentTerms = globalContext.dataPaymentTerms;
	const contractors = globalContext.dataContractors;
	const things = globalContext.dataThings;
	const [domReady, setDomReady] = React.useState(false)
	const [doc, setDoc] = useState()
	const [isEdit, setIsEdit] = useState(false);
	
	const { state: documentDetailState, error: documentDetailError, data: documentDetailData } = useApi(`http://localhost:5000/api/documents/${id}/details`);
	React.useMemo(() =>
	{
		if (documentDetailData != null)
		{
		setDoc(documentDetailData);
		}
	}, [documentDetailState]);
	
	React.useEffect(() => { console.log(doc) }, [doc])
	
	React.useEffect(() => {setDomReady(true)}, [])

	const columns = React.useMemo(() => [
		{
			Header: 'Code', accessor: 'thing.code',
			Cell: function Cell(cell)
			{
              return isEdit ? <Autocomplete
					id="autocomplete-thing"
					size="string"
					autoSelect={true}
					autoComplete={true}
					disabled={isEdit === false ? true : false }
					options={things !== undefined ? things : ""}  
					value={cell.row.original.thing}
					isOptionEqualToValue={(option, value) => option.id === value.id}
					getOptionLabel={(option) => option ? option.code : ""}
					onChange={handleThingChange}
					renderInput={(params) => <TextField size="small" type="text" variant="standard" {...params} />} /> : <Link to={`/warehouse/things/${cell.row.original.thing.id}`}>{cell.value}</Link>;		
			},
		},
		{ Header: 'Name', accessor: 'thing.name' },
		{ Header: "Unit", accessor: "thing.unit.code" },
		{
			Header: 'Quantity', accessor: 'quantity',
			Cell: function Cell(cell, row)
			{
              return isEdit ? <TextField
								id="textfield-quantity"
								size="small"
								type="number"
								variant="standard"
								disabled={isEdit === false ? true : false }
								defaultValue={cell.value}/> : <span>{cell.value}</span>;
				
            },
		Footer: info =>
			{
				const totalQuantity = React.useMemo(
				() => info.rows.reduce((sum, row) => row.values.quantity + sum, 0),[info.rows]
				)
				return <>Total quantity: {totalQuantity}</>
		} },
		{ Header: 'Net price', accessor: 'detailPrice',
			Cell: function Cell(cell, row)
			{
              return isEdit ? <TextField
								id="textfield-detail-price"
								size="small"
								type="number"
								variant="standard"
								disabled={isEdit === false ? true : false }
								defaultValue={cell.value}/> : <span>{cell.value}</span>;
				
            },},
		{ Header: 'Net value', accessor: 'net',  accessor: row =>
		{
			return <>{(row.quantity) * (row.detailPrice)}</>
		}, Footer: info =>
			{
			const totalNetValue = React.useMemo(
				() => info.rows.reduce((sum, row) => row.values['Net value'].props.children + sum, 0),[info.rows]
				)
				return <>Total net value: {totalNetValue}</>
			}
		,Cell: function Cell(cell) {
              return <span>{cell.value}</span>;
            }}], [isEdit]);
	
	const updateMyData = (rowIndex, columnId, value) => {
    /*fetchedData(old =>
      old.map((row, index) => {
        if (index === rowIndex) {
          return {
            ...old[rowIndex],
            [columnId]: value,
          }
        }
        return row
      })
    )*/
  }
	
	const handleCurrencyChange = (event, child) =>
	{
		setDoc((prevState) => ({
				...prevState, currency: {id: child.id, code: event.target.value},	
			}))		
	}
	const handlePaymentFormChange = (event, child) =>
	{
		setDoc((prevState) => ({
				...prevState, paymentForm: { id: child.id, form: event.target.value},
			}))	
	}
	const handlePaymentTermChange = (event, child) =>
	{
		setDoc((prevState) => ({
				...prevState, paymentTerm: {id: child.id, term: event.target.value},	
			}))	
	}
	const handleContractorChange = (event, values) =>
	{
		values !== null ? setDoc((prevState) => ({
				...prevState, contractor: { ...prevState.contractor, id: values.id, name: values.name, ...prevState.address},
			}))	: setDoc((prevState) => ({
				...prevState, contractor: { ...prevState.contractor, id: values.id, name: values.name, ...prevState.address},
			}))
	}
	const handleThingChange = (event, values) =>
	{
		
	}
	
	
	return (
		<div sx={root}>
			{domReady? ReactDOM.createPortal(<DocumentDetailPageToolbar docNumber={doc != null ? doc.docNumber : ""} isEditProp={isEdit} setIsEdit={setIsEdit}/>, document.getElementById("option-toolbar")): null}
			<DocumentHeader>
			<Grid container spacing={2}>	  	 
			<Grid item xs={12} md={4} xl={6}>
					<Grid container spacing={1}>
							<Grid item xs={12} md={12} xl={12}>
								<Autocomplete
									id="autocomplete-contractor"
									autoSelect={true}
									autoComplete={true}
									disabled={isEdit === false ? true : false }
									options={contractors != null ? contractors : ""}
									value={doc !== undefined ? doc.contractor !== undefined ? doc.contractor : "" : ""}
									isOptionEqualToValue={(option, value) => option.id === value.id}
									getOptionLabel={(option) => option.name}
									onChange={handleContractorChange}
									renderInput={(params) => <TextField {...params} variant="outlined" label="Contractor"/>} />
						</Grid>
						<Grid item xs={12} md={12} xl ={6}>
							<Typography sx={{spacing: 2}}>
								{doc !== undefined ? doc.contractor !== undefined ? doc.contractor.address.street + " ": "" : ""}
								{doc !== undefined ? doc.contractor !== undefined ? doc.contractor.address.number + ", ": "" : ""}{<br></br>}
								{doc !== undefined ? doc.contractor !== undefined ? doc.contractor.address.postalCode + " " : "" : ""}
								{doc !== undefined ? doc.contractor !== undefined ? doc.contractor.address.city + ", ": "" : ""}{<br></br>}
								{doc !== undefined ? doc.contractor !== undefined ? doc.contractor.address.country: "" : ""}{<br></br>}
							</Typography>
						</Grid>
						<Grid item xs={12} md={12} xl ={6}>
							<Typography sx={{spacing: 2}}>
								NIP: {doc !== undefined ? doc.contractor !== undefined ? doc.contractor.nip: "" : ""} {<br></br>}
								Regon: {doc !== undefined ? doc.contractor !== undefined ? doc.contractor.regon: "" : ""} {<br></br>}
							</Typography>
						</Grid>
					</Grid>  
			</Grid>
		<Grid item xs={12} md={8} xl={6}>
			<Grid container spacing={2}>
				<Grid item xs={12} md={6} xl={6}>
					<TextField
						type="datetime-local"
						label="Created date:"
						InputProps={{readOnly: true}}
						value={doc !== undefined ? doc.createdAt !== undefined ? doc.createdAt : "" : ""}>
					</TextField>
				</Grid>
				<Grid item xs={12} md={6} xl={6}>
					<TextField
						type="datetime-local"
						label="Requested date:"
						InputProps={isEdit === false ? { readOnly: true } : { readOnly: false }}
						value = {doc !== undefined ? doc.targetDateTime !==undefined ? doc.targetDateTime : "" : ""}>
					</TextField>
				</Grid>
				<Grid item xs={6} md={6} xl={3}>
					<TextField id='select-payment-form'
						variant="outlined"
						label="Payment form"
						select={isEdit === false ? false: true}
						InputProps={isEdit === false ? { readOnly: true} : { readOnly: false} }
						value={doc !== undefined ? doc.paymentForm !== undefined ? doc.paymentForm.form : "" : ""}
						onChange={handlePaymentFormChange}
						size="medium"
					>
						{
							paymentForms != null ? paymentForms.map((option) => (
								<MenuItem key={option.id} value={option.form}>
									{option.form}
								</MenuItem>
							)): null}	   
					</TextField>
				</Grid>
				<Grid item xs={6} md={6} xl={3}>
					<TextField id='select-payment-term'
						variant="outlined"
						label="Payment term"
						select={isEdit === false ? false: true}
						InputProps={isEdit === false ? { readOnly: true} : { readOnly: false} }
						value={doc !== undefined ? doc.paymentTerm !== undefined ? doc.paymentTerm.term : "" : ""}
						onChange={handlePaymentTermChange}
						size="medium"
						>
								{
								paymentTerms != null ? paymentTerms.map((option) => (
								<MenuItem key={option.id} value={option.term}>
									{option.term}
								</MenuItem>
							)): null}	   
					</TextField>
				</Grid>
				<Grid item xs={6} md={6} xl={3}>
						<TextField
							id='select-currency'
							variant="outlined"
							label="Currency"
							select={isEdit === false ? false: true}
							InputProps={isEdit === false ? { readOnly: true} : { readOnly: false} }
							value={doc !== undefined ? doc.currency !== undefined ? doc.currency.code : "" : ""}
							onChange={handleCurrencyChange}
							size="medium">
								{
							currencies != null ? currencies.map((option) => (
								<MenuItem key={option.id} id={option.id} value={option.code}>
									{option.code}
								</MenuItem>
							)): null}	  
						</TextField>
				</Grid>
				
				<Grid item xs={6} md={6} xl={3}>
					<TextField
						id='select-document-status'
						variant="outlined"
						label="Status"
						InputProps={isEdit === false ? { readOnly: true } : { readOnly: true }}
						value={doc !== undefined ? doc.documentStatusEnum !== undefined ? doc.documentStatusEnum : "" : ""}
						size="medium">
					</TextField>
				</Grid>
			</Grid>
			</Grid>	
		</Grid>	
		</DocumentHeader>
		<div>
			<DocumentsDetailsTable
				data={doc !== undefined ? doc.documentDetails : []}
					columns={columns}
					isEdit={isEdit}
					manual
					updateMyData={updateMyData}
			/>
		</div>
	</div>
);
}
export default DocumentsDetailsPage;