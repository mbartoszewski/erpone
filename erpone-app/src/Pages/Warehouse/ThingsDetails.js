import React from 'react'
import { makeStyles } from '@material-ui/core/styles';
import {Divider, IconButton, Typography } from '@material-ui/core';
import { useParams, Link } from "react-router-dom";
import Card from '@material-ui/core/Card';
import CardHeader from '@material-ui/core/CardHeader';
import CardContent from '@material-ui/core/CardContent';
import CardActions from '@material-ui/core/CardActions';
import StarBorderIcon from '@material-ui/icons/StarBorder';
import NavigateNextIcon from '@material-ui/icons/NavigateNext';
import ThingsDetailsItems from '../../Components/Warehouse/ThingsDetailsItems';

const useStyles = makeStyles((theme) => ({
	root: {},
	detailsHeader: {
		width: '100%',
		top: '0',
		position: 'fixed',
		marginLeft: '1px',
	background: 'white'},
	detailsDiv: {
		paddingTop: '90px',
		 margin: '32px' },
	navigateNext: {
		marginLeft: 'auto',
		width: '3em',
	padding: '0'},
	card: {
		marginBottom: '16px'
	},
}))
function ThingsDetails()
{
  	const classes = useStyles();
	const { id } = useParams();
	return (
		<div className={classes.root}>
			<div className={classes.detailsHeader}>
				<h1 style={{ paddingLeft: '8px' }}>Tutaj jest header z kodem i nazwa surowca o danym id: {id}</h1>
				<Divider/>
			</div>
			<div className={classes.detailsDiv}>
			{ThingsDetailsItems.map(each => (
				<React.Fragment key={each.id}>
					<Card className={classes.card} variant="outlined">
						<CardHeader
							title={each.nameHeader}
							subheader={each.subTitle}
							avatar={
									React.createElement(each.avatar)
								}
							action={<IconButton>
									<StarBorderIcon />
									</IconButton>}>
						</CardHeader>
						<CardContent>
							<Typography>Karty można dowolnie sortować, przypinać u góry.</Typography>
							{React.createElement(each.component)}
						</CardContent>
						<CardActions className={classes.navigateNext}>
							<Link to={`/warehouse/things/${id}/` + each.route}>
								<NavigateNextIcon/>
							</Link>
						</CardActions>
					</Card>
				</React.Fragment>
			))}
			</div>
		</div>
		);
}

export default ThingsDetails;