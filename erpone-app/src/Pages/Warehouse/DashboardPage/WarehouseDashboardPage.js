import { Grid } from '@material-ui/core'
import React from 'react'

const WarehouseDashboardPage = () =>
{
	return (
		<div>
			<Grid container spacing={2}>
				<Grid item xl={4} md={6} xs={12}>
					<div style={{backgroundColor:"green", height:"330px"}}>
					<p>Title + odświeżanie</p>
				</div>
				</Grid>
				<Grid item xl={4} md={3} xs={12}>
					<div style={{backgroundColor:"blue", height:"330px"}}>
					<p>stock value + graph in background </p>
				</div>
				</Grid>
				<Grid item xl={4} md={3} xs={12}>
					<div style={{backgroundColor:"yellow", height:"330px"}}>
					<p>Sales value YTD LY text + graph in background</p>
				</div>
				</Grid>
				<Grid item xl={6} md={6} xs={12}>
					<div style={{backgroundColor:"purple", height:"530px"}}>
					<p>Raw material value by date</p>
				</div>
				</Grid>
				<Grid item xl={6} md={6} xs={12}>
					<div style={{backgroundColor:"red", height:"330px"}}>
					<p>Inventory monthly table</p>
				</div>
				</Grid>
				<Grid item xl={6} md={6} xs={12}>
					<div style={{backgroundColor:"orange", height:"330px"}}>
					<p>Finished goods value by date </p>
				</div>
				</Grid>
			
				<Grid item xl={6} md={6} xs={12}>
					<div style={{backgroundColor:"brown", height:"530px"}}>
					<p>inventory value by group chart</p>
				</div>
				</Grid>
			</Grid>
	</div>
)
}

export default WarehouseDashboardPage;