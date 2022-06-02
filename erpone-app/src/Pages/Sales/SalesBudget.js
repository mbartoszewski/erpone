import React from "react";
import { FormControl, Grid, IconButton, InputLabel, MenuItem, Select, TextField, Typography } from "@mui/material";
import DashboardTile from "../../Components/DashboardTile";
import KeyboardArrowDownIcon from '@mui/icons-material/KeyboardArrowDown';
import KeyboardArrowUpIcon from '@mui/icons-material/KeyboardArrowUp';

const SalesBudget = () =>
{
	const numbers = [{ id: 1, value: 3444 }, { id: 2, value: 3744 }, { id: 3, value: 4444 }, { id: 4, value: 4444 }, { id: 5, value: 6444 }, { id: 6, value: 7444 }, { id: 7, value: 8444 }, { id: 8, value: 6744 },
	{ id: 9, value: 6444 }, { id: 10 , value: 5644}, { id: 11 , value: 3444}, { id: 12 , value: 6444}]
	const [unit, setUnit] = React.useState('');
	const [budgetTitle, setBudgetTitle] = React.useState('')
	const [budgetYear, setBudgetYear] = React.useState(new Date().getFullYear())

  	const handleUnitChange = (e) => {
    	setUnit(e.target.value);
	};
	
	const handleYearChange = (increase) =>
	{
	   increase === true ? setBudgetYear(budgetYear +1) : setBudgetYear(budgetYear -1)
  	}

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
			<Grid item xl={5} md={5} xs={12}>	
					<TextField
						variant="standard"
						fullWidth={true}
					label="Budget title"
					size="h2"
					defaultValue={budgetTitle}/>
			</Grid>
			<Grid item xl={1} md={1} xs={12}>
				<FormControl fullWidth={true}>
					<InputLabel>Budget unit</InputLabel>
					<Select
						fullWidth={true}
					 	value={unit}
         			 	label="Budget unit"
						onChange={handleUnitChange}>
						<MenuItem value={"szt"}>szt</MenuItem>
						<MenuItem value={"PLN"}>PLN</MenuItem>
					</Select>
				</FormControl>
				
			</Grid>
			<Grid item xl={6} md={6} xs={12}>	
					<Typography>Created on: 2021-02-02 Created by: user1 Last modified on: 2021-04-02 Last modified by: user 7</Typography>
			</Grid>
			<Grid item xl={2} md={2} xs={12}>
				{numbers.map(n =>
				{
					return <TextField
						variant="standard"
						fullWidth={true}
						label={n.id}
						defaultValue={n.value}/>
				})}
				
				
			</Grid>
			<Grid item xl={10} md={10} xs={12} style={{backgroundColor: "green"}}>
				
			</Grid>
		</Grid>
	</div>);
}

export default SalesBudget;