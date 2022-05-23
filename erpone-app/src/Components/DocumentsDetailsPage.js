import React, {useState, useContext, useEffect} from 'react'
import { useParams, useLocation} from "react-router-dom";
import { Grid, MenuItem, TextField, Typography, Button, ButtonGroup, Stack } from '@mui/material'
import { styled, } from '@mui/material/styles';
import DocumentsDetailsTable from './DocumentsDetailsTable'
import { apiStates, useApi, useApii } from './Fetch'
import { globalStateContext } from '../Pages/ErpOneApp';
import ReactDOM from 'react-dom';
import DocumentDetailPageToolbar from './DocumentDetailPageToolbar';
import Autocomplete from '@mui/material/Autocomplete';
import { docStates} from './Helpers';
import AddIcon from '@mui/icons-material/Add';
import RemoveIcon from '@mui/icons-material/Remove';
import Checkbox from '@mui/material/Checkbox'

const DocumentHeader = styled('div')(({ theme }) => ({
  position: 'sticky',
		top: '0px',
		padding: '0.5em',
		marginBottom: '0.4em',
		borderBottom: `4px solid ${theme.palette.divider}`,
		background: "white",
		zIndex: "1"
}));
const DocumentTable = styled('div')(({ theme }) => ({
 
}));

const DocumentsDetailsPage = (props) =>
{
	const location = useLocation()
	const { id } = useParams();
	const [docState, setDocState] = useState(location.state != undefined && location.state.docState != undefined? location.state.docState : docStates.VIEW)
	const globalContext = useContext(globalStateContext);
	const currencies = globalContext.dataCurrencies;
	const warehouses = globalContext.dataWarehouses;
	const paymentForms = globalContext.dataPaymentForms;
	const paymentTerms = globalContext.dataPaymentTerms;
	const contractors = globalContext.dataContractors;
	const things = globalContext.dataThings;
	const { state: documentNumberState, error: documentNumberError, data: documentNumberData }  = useApi(`http://localhost:5000/api/documents/number?type=${location.title}`); 
	const [domReady, setDomReady] = React.useState(false)
	const [selectedRows, setSelectedRows] = React.useState(null)
	const [doc, setDoc] = useState({
	documentStatusEnum: "open",
	description: "",
    documentTypeEnum: location.title != undefined? location.title : undefined,
    relatedDocuments: null,
    targetDateTime: undefined,
    contractor: {address: {}},
    documentCurrency: {},
    paymentForm: {},
    paymentTerm: {},
	documentDetails: []
	})
	const [originalDoc, setOriginalDoc] = useState();
	const { state: documentDetailState, error: documentDetailError, data: documentDetailData } = useApi(`http://localhost:5000/api/documents/${id}/details`);
	const [{data}, doFetch] = useApii(null);

	React.useMemo(() =>
	{
		if (documentDetailData != null)
		{
			setDoc(documentDetailData);
			setOriginalDoc(documentDetailData)
		} else
		{
			
		setDoc((prevState) => ({
				...prevState, docNumber: documentNumberState === apiStates.SUCCESS ? documentNumberData.type : "",	
			}))	
		}
	
	}, [documentDetailData, documentNumberData]);
	
	React.useEffect(() => { setDomReady(true) }, [])

	const handleCurrencyChange = (event, child) =>
	{
		setDoc((prevState) => ({
				...prevState, documentCurrency: {id: child.props.id, code: event.target.value},	
			}))		
	}
	const handlePaymentFormChange = (event, child) =>
	{
		setDoc((prevState) => ({
				...prevState, paymentForm: { id: child.props.id, form: event.target.value},
			}))	
	}
	const handlePaymentTermChange = (event, child) =>
	{
		setDoc((prevState) => ({
				...prevState, paymentTerm: {id: child.props.id, term: event.target.value},	
			}))	
	}
	const handleContractorChange = (event, values) =>
	{
		contractors.map((c) =>
		{
			if (values != null && values.id === c.id) {
			setDoc((prevState) => ({
				...prevState, contractor: c,
			}))
		}
		})
	}

	const handleDescriptionChange = (event) =>
	{
		setDoc((prevState) => ({
				...prevState, description: event.target.value,	
			}))	
	}

	const handleThingChange = (rowIndex, columnId, value) =>
	{
		setDoc((prevState) => ({
			...prevState, documentDetails: [...prevState.documentDetails.map((row, index) =>
			{
				if (index === rowIndex.index)
				{
					if (columnId.id !== 'thing.code')
					{
						return {
						...prevState.documentDetails[rowIndex.index],
						[columnId.id]: parseFloat(value),	
					}
					} else
					{	
						return {
						...prevState.documentDetails[rowIndex.index],
							thing: data.data,	
						}
						}
					
				}
					return row;			
			})],
		}))
	}
	const handleDateTimeChange = (event) =>
	{
		setDoc((prevState) => ({
			...prevState, targetDateTime: event.target.value
		}))
	}
	const handleAddItemClick = () => 
	{
		setDoc((prevState) => ({
			...prevState, documentDetails: [...prevState.documentDetails, {}],
		}))
	}
	const handleDeleteItemClick = () => 
	{
		setDoc((prevState) => ({
			...prevState, documentDetails: [...prevState.documentDetails.filter(dd => !selectedRows.includes(dd.id))],
		}))
	}
	const IndeterminateCheckbox = React.forwardRef(({ indeterminate, ...rest }, ref) =>
{
	const defaultRef = React.useRef()
	const resolvedRef = ref || defaultRef

	React.useEffect(() =>
	{
		resolvedRef.current.indeterminate = indeterminate
	}, [resolvedRef, indeterminate])

	return (
		<>
			<Checkbox ref={resolvedRef} {...rest}/>
		</>
	)
})
	const columns = React.useMemo(() => [
		{
					id: 'selection',
					// The header can use the table's getToggleAllRowsSelectedProps method
					// to render a checkbox
					Header: ({ getToggleAllRowsSelectedProps }) => (
						<div>
							<IndeterminateCheckbox {...getToggleAllRowsSelectedProps()} />
						</div>
					),
					// The cell can use the individual row's getToggleRowSelectedProps method
					// to the render a checkbox
			Cell: ({ row }) => (		
						<div>
							<IndeterminateCheckbox {...row.getToggleRowSelectedProps()} />
						</div>
					),
				},
		{
			Header: 'Code', accessor: 'thing.code',
			Cell: function Cell({cell, row, column, handleThingChange, value: initialValue})
			{
				const [value, setValue] = React.useState(initialValue)
				const onChange = (event, nValue) =>
				{
					if (nValue !== null)
					{
					setValue(nValue.code)
					doFetch({url: `http://localhost:5000/api/things/${nValue.id}/`})
					}
				}

				const onBlur = () =>
				{
					handleThingChange(row, column, value)
				}

				React.useEffect(() =>
				{
					setValue(initialValue)
				}, [initialValue])

              const code =  <Autocomplete
					id="autocomplete-thing"
				  	size="string"
				  	sx={{minWidth:"150px"}}
				  	type="text"
				  	onChange={(event, nValue) => {onChange(event, nValue)}}
					onBlur={onBlur}
				  	fullWidth={true}
					disabled={(docState === docStates.EDIT || docState === docStates.ADD) ? false : true }
				  	options={things !== null ? things : [0]} 
				 	value={{code: value}} 
				  	getOptionLabel={(option) => option.code}
					isOptionEqualToValue={(option, value) => option.code == value.code}
					renderInput={(params) => <TextField size="string" type="text" variant="standard" {...params} />} />;	
				
				return code;
			},
		},
		{ Header: 'Name', accessor: 'thing.name' },
		{ Header: "Unit", accessor: "thing.unit.code" },
		{Header: 'Quantity', accessor: 'quantity',
			Cell: function Cell({row, column, handleThingChange, value: initialValue})
			{
				const [value, setValue] = React.useState(initialValue)
				const onChange = e =>
				{
					setValue(e.target.value)
				}

				const onBlur = () =>
				{
					handleThingChange(row, column, value)
				}

				React.useEffect(() =>
				{
					setValue(initialValue)
				}, [initialValue])

				const detailQuantity = <TextField
								id="textfield-detail-quantity"
								size="string"
								type="number"
					variant="standard"
					fullWidth={true}
					InputProps={(docState === docStates.EDIT || docState === docStates.ADD) ? { readOnly: false} : { readOnly: true }}
					helperText={value <= 0 ? "Error: Quantity must be higher than 0" : false}
					error={value <= 0 ? true : false}
					onChange={onChange}
					onBlur={onBlur}
					value={value}/>
								
				return detailQuantity
				
            },
		Footer: info =>
			{
			const totalQuantity = React.useMemo(() => info.rows.reduce((sum, row) => row.values['quantity'] + sum, 0), [info.rows])
				return <>Total quantity: {totalQuantity}</>
		}, },
		{ Header: 'Net price', accessor: 'detailPrice',
			Cell: function Cell({row, column, handleThingChange, value: initialValue})
			{
				const [value, setValue] = React.useState(initialValue)
				const onChange = e =>
				{
					setValue(e.target.value)
				}

				const onBlur = () =>
				{
					handleThingChange(row, column, value)
				}

				React.useEffect(() =>
				{
					setValue(initialValue)
				}, [initialValue])

				const detailPrice = <TextField
					id="textfield-detail-price"
					size="string"
					type="number"
					variant="standard"
					fullWidth={true}
					inputProps={{ min: 0} }
					onChange={onChange}
					onBlur={onBlur}
					value={value}
					helperText={value <= 0 ? "Error: Price must be higher than 0" : false}
					error={value <= 0 ? true : false} />
								
				return detailPrice
				
            },},
		{ Header: 'Net value', accessor: 'net',  accessor: row =>
		{
			return <>{(row.quantity) * (row.detailPrice)}</>
		}, Footer: info =>
			{
			const totalNetValue = React.useMemo(() => info.rows.reduce((sum, row) => row.values['Net value'].props.children + sum, 0),[info.rows])
				return <>Total net value: {totalNetValue}</>
			}
		,Cell: function Cell(cell) {
              return <span>{cell.value}</span>;
            }}], [docState]);

	let current_datetime = new Date()
	let formatted_date = current_datetime.getFullYear() + "-" + (current_datetime.getMonth() + 1) + "-" + current_datetime.getDate() + "T" + current_datetime.getHours() + ":" + current_datetime.getMinutes() + ":" + current_datetime.getSeconds() 

	return (
		<div >
			{domReady ? ReactDOM.createPortal(<DocumentDetailPageToolbar docNumber={doc != null ? doc.docNumber : ""} docStateProp={docState} setDocState={setDocState} setDoc={setDoc} originalDoc={originalDoc} setOriginalDoc={setOriginalDoc} doc={doc}/>, document.getElementById("option-toolbar")): null}
			<DocumentHeader>
			<Grid container spacing={2}>	  	 
			<Grid item xs={12} md={4} xl={6}>
					<Grid container spacing={1}>
							<Grid item xs={12} md={12} xl={12}>
								<Autocomplete
									id="autocomplete-contractor"
									size="small"
									autoSelect={true}
									autoComplete={true}
									disabled={(docState === docStates.EDIT || docState === docStates.ADD) ? false : true }
									options={contractors !== null ? contractors : []}
									value={doc !== undefined && doc.contractor !== undefined ? doc.contractor : ""}
									isOptionEqualToValue={(option, value) => option.id === value.id}
									getOptionLabel={(option) => option.name}
									onChange={handleContractorChange}
									renderInput={(params) => <TextField {...params} variant="outlined" label="Contractor" fullWidth={true}/>} />
						</Grid>
						<Grid item xs={12} md={6} xl ={6}>
							<Typography sx={{spacing: 1}}>
								{doc !== undefined && doc.contractor !== undefined && doc.contractor.address.street ? doc.contractor.address.street + " " : ""}
								{doc !== undefined && doc.contractor !== undefined && doc.contractor.address && doc.contractor.address.number ? doc.contractor.address.number + ", " : ""}{<br></br>}
								{doc !== undefined && doc.contractor !== undefined && doc.contractor.address && doc.contractor.address.postalCode? doc.contractor.address.postalCode + " " : "" }
								{doc !== undefined && doc.contractor !== undefined && doc.contractor.address && doc.contractor.address.city? doc.contractor.address.city + ", ": "" }{<br></br>}
								{doc !== undefined && doc.contractor !== undefined && doc.contractor.address && doc.contractor.address.country ? doc.contractor.address.country : ""}{<br></br>}
								NIP: {doc !== undefined && doc.contractor !== undefined && doc.contractor.nip !== undefined ? doc.contractor.nip + `\u2003` : `\u2003`}
								Regon: {doc !== undefined && doc.contractor !== undefined && doc.contractor.regon !== undefined ? doc.contractor.regon : ""}
							</Typography>
						</Grid>
						<Grid item xs={12} md={6} xl ={6}>
								<TextField
									InputProps={(docState === docStates.EDIT || docState === docStates.ADD) ? { readOnly: false} : { readOnly: true}}
									value={doc !== undefined ? doc.description : ""}
									fullWidth={true}
									onChange={handleDescriptionChange}
									multiline
									rows={3}
          							/>
						</Grid>
					</Grid>  
			</Grid>
		<Grid item xs={12} md={8} xl={6}>
			<Grid container spacing={2}>
				<Grid item xs={12} md={6} xl={6}>
					<TextField
									type="datetime-local"
									size="small"
						label="Created date:"
						InputProps={{ readOnly: true }}
						InputLabelProps={{ shrink: true }}
						value={doc !== undefined && doc.createdAt !== undefined ? doc.createdAt : formatted_date}>
					</TextField>
				</Grid>
				<Grid item xs={12} md={6} xl={6}>
					<TextField
									type="datetime-local"
									size="small"
						label="Requested date:"
						onChange={handleDateTimeChange}
						InputProps={(docState === docStates.EDIT || docState === docStates.ADD) ? { readOnly: false} : { readOnly: true}}
						InputLabelProps={{ shrink: true }}
						value = {doc !== undefined && doc.targetDateTime !==undefined ? doc.targetDateTime : formatted_date}>
					</TextField>
				</Grid>
				<Grid item xs={6} md={6} xl={3}>
					<TextField id='select-payment-form'
						variant="outlined"
						label="Payment form"
						fullWidth={true}
						select={ (docState === docStates.EDIT || docState === docStates.ADD) ? true: false}
						InputProps={(docState === docStates.EDIT || docState === docStates.ADD) ? { readOnly: false } : { readOnly: true }}
						InputLabelProps={{ shrink: true }}
						value={doc !== undefined && doc.paymentForm !== undefined ? doc.paymentForm.form : ""}
						onChange={handlePaymentFormChange}
						size="small"
					>
						{
							paymentForms != null ? paymentForms.map((option) => (
								<MenuItem key={option.id} value={option.form} id={option.id}>
									{option.form}
								</MenuItem>
							)): null}	   
					</TextField>
				</Grid>
				<Grid item xs={6} md={6} xl={3}>
					<TextField id='select-payment-term'
						variant="outlined"
						label="Payment term"
						fullWidth={true}
						select={(docState === docStates.EDIT || docState === docStates.ADD) ? true : false}
						InputProps={(docState === docStates.EDIT || docState === docStates.ADD) ? { readOnly: false } : { readOnly: true }}
						InputLabelProps={{ shrink: true }}
						value={doc !== undefined ? doc.paymentTerm !== undefined ? doc.paymentTerm.term : "" : ""}
						onChange={handlePaymentTermChange}
						size="small"
						>
								{
								paymentTerms != null ? paymentTerms.map((option) => (
								<MenuItem key={option.id} value={option.term} id={option.id}>
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
							fullWidth={true}
							select={(docState === docStates.EDIT || docState === docStates.ADD) ? true: false}
							InputProps={(docState === docStates.EDIT || docState === docStates.ADD) ? { readOnly: false } : { readOnly: true }}
							InputLabelProps={{ shrink: true }}
							value={doc !== undefined ? doc.documentCurrency !== undefined ? doc.documentCurrency.code : "" : ""}
							onChange={handleCurrencyChange}
							size="small">
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
						InputProps={(docState === docStates.EDIT || docState === docStates.ADD) ? { readOnly: true } : { readOnly: true }}
						value={doc !== undefined ? doc.documentStatusEnum !== undefined ? doc.documentStatusEnum : "" : ""}
						size="small">
					</TextField>
							</Grid>
							<Grid item xs={12} md={12} xl={12} sx={(docState === docStates.EDIT || docState === docStates.ADD) ? null : {display:"none"} }>
				<ButtonGroup variant="outlined" size="small" >
						<Button
							size='small'
							startIcon={<AddIcon/>}
							color='inherit'
							onClick={handleAddItemClick}>
							Add item
						</Button>
						<Button
							size='smal'
							startIcon={<RemoveIcon/>}
							color='inherit'
							onClick={handleDeleteItemClick}>
							Delete item
						</Button>
				</ButtonGroup>		
					</Grid>
			</Grid>	
			</Grid>	
			
		</Grid>	
			</DocumentHeader>
			<DocumentTable>
					<DocumentsDetailsTable
						data={doc != undefined && doc.documentDetails != null ? doc.documentDetails : [0]}
						columns={columns}
						docState={docState}
						manual
						setSelected={setSelectedRows}
						handleThingChange={handleThingChange}
					/>
			</DocumentTable>		
	</div>
);
}
export default DocumentsDetailsPage;