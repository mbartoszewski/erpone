import React from "react";
import { Checkbox, FormControl, FormControlLabel, Grid, IconButton, InputLabel, MenuItem, Select, TextField, Typography } from "@mui/material";
import DashboardTile from "../../Components/DashboardTile";
import KeyboardArrowDownIcon from '@mui/icons-material/KeyboardArrowDown';
import KeyboardArrowUpIcon from '@mui/icons-material/KeyboardArrowUp';
import {apiStates, useFetch} from '../../Components/Fetch'
import
	{
  ComposedChart,
  Line,
  Area,
  Bar,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  Legend,
  ResponsiveContainer,
} from 'recharts';

const SalesBudget = () =>
{
	const numbers = [{ id: 1, value: 3444, sales: 5565 }, { id: 2, value: 3744, sales: 2595 }, { id: 3, value: 4444, sales: 3565 }, { id: 4, value: 4444, sales: 4466 },
		{ id: 5, value: 6444, sales: 8965 }, { id: 6, value: 7444, sales: 8555  }, { id: 7, value: 8444, sales: 7586  }, { id: 8, value: 6744, sales: 5896  },
	{ id: 9, value: 6444, sales: 9856  }, { id: 10 , value: 5644, sales: 8588 }, { id: 11 , value: 3444, sales: 6589 }, { id: 12 , value: 6444, sales: 4325 }]
	const [budgetUnit, setBudgetUnit] = React.useState('');
	const [budgetTitle, setBudgetTitle] = React.useState('')
	const [budgetYear, setBudgetYear] = React.useState(new Date().getFullYear())
	const [isEdit, setIsEdit] = React.useState(false)
	const [{data: budgetYearList}, doFetch] = useFetch(`http://localhost:5000/api/budget/all?type=sales&year=${budgetYear}`)
	const [{data: budgetDetails}, doBudgetDetails] = useFetch(null)
	const handleTitleChange = (e) =>
	{
		doBudgetDetails({url: `http://localhost:5000/api/budget/${e.target.value}`, option: null})
		setBudgetTitle(e.target.value);
	};
  	
	const handleUnitChange = (e) =>
	{
    	setBudgetUnit(e.target.value);
	};
	
	const handleYearChange = (increase) =>
	{
		const year = increase === true ? budgetYear +1 : budgetYear -1
	   	increase === true ? setBudgetYear(budgetYear +1) : setBudgetYear(budgetYear -1)
	   	doFetch({url: `http://localhost:5000/api/budget/all?type=sales&year=${year}`, option: null})
  	}
	React.useEffect(() =>
	{
		if(budgetDetails !==null && budgetDetails !== undefined) console.log(budgetDetails)
	}, [budgetDetails])
	return (<div>
		<Grid container spacing={2}>
			<Grid item xl={5} md={5} xs={4}>
				 <DashboardTile
					title="Quick summary"
					subHeader="refreshed at 19:22"
					subContent={
						<div>
						<Typography>Sales goal: 32.33 mln €</Typography>
						<Typography>Profit goal: 11.22 mln €</Typography>
						<Typography>Margin goal: 31.2%</Typography>
							</div>
					} />
			</Grid>
			<Grid item xl={5} md={5} xs={4}>
				 <DashboardTile
					title="Goals vs real:"
					subHeader="refreshed at 19:22"
					subContent={
						<div>
						<Typography>Sales fullfilment: 8 months (2022-12-30)</Typography>
						<Typography>Actual average profit: 5.71 mln € (-50%)</Typography>
						<Typography>Actual average margin: 15.6% (-50%)</Typography>
							</div>
					} />
			</Grid>
			<Grid item xl={2} md={2} xs={4}>
				<IconButton size="large" aria-label="up" onClick={() => { handleYearChange(true) }}>
              		<KeyboardArrowUpIcon/>
				</IconButton>
				<Typography variant="h1" align="center">{budgetYear}</Typography>
				<IconButton size="large"  aria-label="down" onClick={() => { handleYearChange(false) }}>
              		<KeyboardArrowDownIcon/>
            	</IconButton>
			</Grid>
			<Grid item xl={8} md={8} xs={8}>	
					<FormControl fullWidth={true}>
					<InputLabel>Budget title</InputLabel>
					<Select
						variant="outlined"
						value={budgetTitle}
         			 	label="Budget title"
						onChange={handleTitleChange}>
						{budgetYearList.data !== null ? budgetYearList.data.map((o) =>
						{
							return <MenuItem key={o.id} value={o.id}>{o.budgetName}</MenuItem>
						}): null}
					</Select>
				</FormControl>
			</Grid>
			<Grid item xl={4} md={4} xs={4}>
				<FormControl fullWidth={true}>
					<InputLabel>Budget unit</InputLabel>
					<Select
						variant="outlined"
						value={budgetUnit}
						disabled={isEdit === false ? true : false }
         			 	label="Budget unit"
						onChange={handleUnitChange}>
						<MenuItem value={"szt"}>szt</MenuItem>
						<MenuItem value={"PLN"}>PLN</MenuItem>
					</Select>
				</FormControl>
				
			</Grid>
			<Grid item xl={8} md={8} xs={10}>	
				<Typography>Created on: {budgetDetails.data !== null ? budgetDetails.data.createdAt : ""} Created by: user1	Last modified on: {budgetDetails.data !== null ? budgetDetails.data.updatedAt : ""} Last modified by: user 7</Typography>
			</Grid>
			<Grid item xl={4} md={4} xs={2}>	
				<FormControlLabel control={<Checkbox/>} label="Active" />
			</Grid>
			<Grid item xl={2} md={2} xs={12}>
				{
				budgetDetails.data !== null && budgetDetails.data !== undefined && budgetDetails.httpStatus === 200 && budgetDetails.state !== apiStates.LOADING ? budgetDetails.data.budgetValues.map((n) =>
				{
					return <TextField
						variant="standard"
						fullWidth={true}
						InputProps={isEdit === false ? { readOnly: true} : { readOnly: false }}
						label={n.month}
						value={n.value}/>
				}) : null}
				
				
			</Grid>
			<Grid item xl={10} md={10} xs={12}>
				<ResponsiveContainer width="100%" height="100%">
					<ComposedChart
					width={500}
					height={400}
					data={numbers}
					margin={{
						top: 20,
						right: 20,
						bottom: 20,
						left: 20,
					}}
					>
					<CartesianGrid stroke="#f5f5f5" />
					<XAxis dataKey="id"/>
					<YAxis />
					<Tooltip />
					<Legend />
					<Bar dataKey="value" barSize={45} fill="#413ea0" />
					<Line type="monotone" dataKey="sales" stroke="#ff7300" />
					</ComposedChart>
			</ResponsiveContainer>
			</Grid>
		</Grid>
	</div>);
}

export default SalesBudget;