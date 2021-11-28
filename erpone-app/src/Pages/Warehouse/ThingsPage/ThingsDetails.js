import React from 'react'
import {Grid, Typography } from '@mui/material';
import { useParams, Link } from "react-router-dom";
import ThingsDetailsItems from './ThingsDetailsItems';
import { apiStates, useApi } from '../../../Components/Fetch'
import DashboardTile from '../../../Components/DashboardTile';
import ReactDOM from 'react-dom'

function ThingsDetails()
{
	const { id } = useParams();
	const { state: thingDetailsState, error: thingDetailsError, data: thingDetails } = useApi(`http://localhost:5000/api/things/${id}`);
	const [domReady, setDomReady] = React.useState(false)

  React.useEffect(() => {
    setDomReady(true)
  })
	
	return (
		<div >
			{domReady?ReactDOM.createPortal(
				<Grid container >
					<Grid item xs={6} md={6} xl={6}>
						<Typography sx={ {fontSize: '1.2em'}}>{thingDetails !== null? thingDetails.code: ""}</Typography>
					</Grid>
					<Grid container item justifyContent="flex-end" xs={6} md={6} xl={6}>
						<Grid item>
							<Typography sx={ {fontSize: '1.2em'}}>Stan: {thingDetails !== null? thingDetails.quantity: ""} {thingDetails !== null? thingDetails.unit.code: ""}</Typography>
						</Grid>
					</Grid>
					<Grid item xs={6} md={6} xl={6}>
						<Typography sx={ {fontSize: '0.9em'}}>{thingDetails !== null? thingDetails.name: ""}</Typography>
					</Grid>
					<Grid container item justifyContent="flex-end" xs={6} md={6} xl={6}>
						<Grid item>
							<Typography sx={ {fontSize: '0.9em'}}>Stan handlowy: 38522 szt.</Typography>
						</Grid>
					</Grid>
				</Grid>, document.getElementById("option-toolbar")): null}
		
			<div>
				<Grid container spacing={2}>
					{ThingsDetailsItems.map(each => (
						<React.Fragment key={each.id}>
							<Grid item xs={12} xm={6} xl={6}>
								<DashboardTile
									title={each.nameHeader}
									subheader={each.subTitle}
									component={React.createElement(each.component)}
								/>
							</Grid>
				</React.Fragment>
			))}
				</Grid>
			</div>
		</div>
		);
}

export default ThingsDetails;