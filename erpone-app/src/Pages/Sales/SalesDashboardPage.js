import { Grid } from '@material-ui/core'
import React from 'react'

const SalesDashboardPage = () =>
{
	return (
		<div>
			<Grid container spacing={2}>
				<Grid item xl={4} md={2} xs={12}>
					<div style={{backgroundColor:"green", height:"330px"}}>
					<p>Title + odświeżanie</p>
				</div>
				</Grid>
				<Grid item xl={2} md={2} xs={6}>
					<div style={{backgroundColor:"blue", height:"330px"}}>
					<p>Sales value YTD </p>
				</div>
				</Grid>
				<Grid item xl={2} md={2} xs={6}>
					<div style={{backgroundColor:"yellow", height:"330px"}}>
					<p>Sales value YTD LY</p>
				</div>
				</Grid>
				<Grid item xl={2} md={2} xs={6}>
					<div style={{backgroundColor:"red", height:"330px"}}>
					<p>Budget realization in % YTD</p>
				</div>
				</Grid>
				<Grid item xl={2} md={2} xs={6}>
					<div style={{backgroundColor:"orange", height:"330px"}}>
					<p>Budget realization in % YTD LY</p>
				</div>
				</Grid>
				<Grid item xl={4} md={4} xs={12}>
					<div style={{backgroundColor:"purple", height:"530px"}}>
					<p>Sales summary table</p>
				</div>
				</Grid>
				<Grid item xl={4} md={4} xs={12}>
					<div style={{backgroundColor:"brown", height:"530px"}}>
					<p>Sales sumary chart</p>
				</div>
				</Grid>
				<Grid item xl={4} md={4} xs={12}>
					<div style={{backgroundColor:"grey", height:"530px"}}>
					<p>Sales summary by customer graph</p>
				</div>
				</Grid>
			</Grid>
	</div>
)
}

export default SalesDashboardPage;