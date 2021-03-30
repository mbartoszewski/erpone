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
	return (
		<div className={classes.root}>
			<Box className={classes.detailsHeader}>
				<Typography className={classes.headerTitle}>Tutaj jest header z kodem surowca o danym id: {id}</Typography>
				<Typography className={classes.headerQuantity}>Stan: 58522 szt.</Typography>
				<Typography className={classes.headerDescription}>Tutaj jest header z opisem surowca o danym id: {id}</Typography>
				<Typography className={classes.headerSalesQuantity}>Stan handlowy: 38522 szt.</Typography>

			</Box>
			<div className={classes.detailsDiv}>
				<Grid container xs={12} spacing={2}>
					{ThingsDetailsItems.map(each => (
						<React.Fragment key={each.id}>
							<Grid item xs={12} xm={6} xl={6}>
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
							{React.createElement(each.component)}
						</CardContent>
						<CardActions className={classes.navigateNext}>
							<Link to={`/warehouse/things/${id}/` + each.route}>
								<NavigateNextIcon/>
							</Link>
						</CardActions>
					</Card>
							</Grid>
				</React.Fragment>
			))}
				</Grid>
			</div>
		</div>
		);
}

export default ThingsDetails;