import { Grid } from '@material-ui/core'
import React from 'react'

const WarehouseDashboard = () =>
{
	return (<div>
		<Grid container xs={12}>
			<Grid item xs={12} sm={12} xl={12}>
				<div style={{backgroundColor:"green", height:"330px"}}>
					<p>Pierwszy grid z wykresem </p>
				</div>
			</Grid>
			<Grid item xs={12} sm={6} xl={6}>
				<div style={{backgroundColor:"red", height:"280px"}}>
					<p>Podsumowanie zamówień do uszykowania i wysyłki</p>
				</div>
			</Grid>
			<Grid item xs={12} sm={6} xl={6}>
				<div style={{backgroundColor:"blue", height:"280px"}}>
					<p>Podsumowanie zleceń do uszykowania na produkcję</p>
				</div>
			</Grid>
			<Grid item xs={12} sm={12} xl={12}>
				<div style={{backgroundColor:"grey", height:"330px"}}>
					<p>Widok tygodnia z harmonogramem dostaw</p>
				</div>
			</Grid>
		</Grid>
	</div>);
}

export default WarehouseDashboard;