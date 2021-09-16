import React from 'react'
import { makeStyles } from '@material-ui/core/styles';
import {Box, Grid, IconButton, Typography } from '@material-ui/core';
import { useParams, Link } from "react-router-dom";
import Card from '@material-ui/core/Card';
import CardHeader from '@material-ui/core/CardHeader';
import CardContent from '@material-ui/core/CardContent';
import CardActions from '@material-ui/core/CardActions';
import StarBorderIcon from '@material-ui/icons/StarBorder';
import NavigateNextIcon from '@material-ui/icons/NavigateNext';
import ThingsDetailsItems from './ThingsDetailsItems';
import { apiStates, useApi } from '../../../Components/Fetch'
import DashboardTile from '../../../Components/DashboardTile';

const useStyles = makeStyles((theme) => ({
	root: {},
	detailsHeader: {
		top: '0',
		zIndex: '1000',
		backgroundColor: '#ddf6dd',
		position: 'sticky',
		borderBottom: '1px solid',
		display: 'flex',
		flexDirection: 'row',
		flexWrap: 'wrap',
		paddingLeft: theme.spacing(1),
		paddingRight: theme.spacing(1),
	},
	headerTitle: {
		flex: '3 3 70%',
		fontSize: '1.3em',
		fontWeight: 'bold'
	},
	headerDescription: {
		flex: '1 0 70%',
		fontSize: '1em'
	},
	headerQuantity: {
		flex: '1 1 25%',
		marginLeft: 'auto',
		textAlign: 'right',
		fontSize: '1.3em',
		fontWeight: 'bold'
	},
	headerSalesQuantity: {
			flex: '1 1 25%',
			marginLeft: 'auto',
			textAlign: 'right',
			fontSize: '1em',
		},
	detailsDiv: {
		margin: theme.spacing(3),
 },
	navigateNext: {
		position: 'relative',
		flex: '0 0 100%',
		justifyContent: 'flex-end'
	},
	card: {
		marginBottom: theme.spacing(2)
	},
}))
function ThingsDetails()
{
  	const classes = useStyles();
	const { id } = useParams();
	const { state: thingDetailsState, error: thingDetailsError, data: thingDetails } = useApi(`http://localhost:5000/api/things/${id}`);
	return (
		<div className={classes.root}>
			<Box className={classes.detailsHeader}>
				<Typography className={classes.headerTitle}>{thingDetails != null? thingDetails.code: ""}</Typography>
				<Typography className={classes.headerQuantity}>Stan: {thingDetails != null? thingDetails.quantity: ""} {thingDetails != null? thingDetails.unit.code: ""}</Typography>
				<Typography className={classes.headerDescription}>{thingDetails != null? thingDetails.name: ""}</Typography>
				<Typography className={classes.headerSalesQuantity}>Stan handlowy: 38522 szt.</Typography>

			</Box>
			<div className={classes.detailsDiv}>
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