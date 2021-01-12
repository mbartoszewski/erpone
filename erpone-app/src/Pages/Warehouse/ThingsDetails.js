import React from 'react'
import { makeStyles } from '@material-ui/core/styles';
import {IconButton, Typography } from '@material-ui/core';
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
		zIndex: '1000',
		backgroundColor: '#ddf6dd',
		position: 'sticky',
		borderBottom: '1px solid'
	},
	headerTitle: {
		position: 'relative',
		flex: '0 0 100%',
		marginLeft: theme.spacing(1),
		marginRight: theme.spacing(1),
		fontSize: '1.5em',
		fontWeight: 'bold'
	},
	headerDescription: {
		position: 'relative',
		flex: '0 0 100%',
		marginLeft: theme.spacing(1),
		marginRight: theme.spacing(1),
		fontSize: '1em'
	},
	headerEditThing: {
		position: 'relative',
		flex: '0 0 100%'
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
			<div className={classes.detailsHeader}>
				<Typography className={classes.headerTitle}>Tutaj jest header z kodem surowca o danym id: {id}</Typography>
				<Typography className={classes.headerDescription}>Tutaj jest header z opisem surowca o danym id: {id}</Typography>
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