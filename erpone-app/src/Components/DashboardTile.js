import React from "react";
import { makeStyles } from "@material-ui/styles";
import Card from '@material-ui/core/Card';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';
import CardHeader from '@material-ui/core/CardHeader';
const useStyles = makeStyles((theme) => ({
	Card: {
		backgroundColor: "#f4f4f4"
	}
}));

const DashboardTile = (props) =>
{
	const classes = useStyles();
	
	return (
		<Card className= {classes.Card}>
			<CardHeader
				titleTypographyProps={{variant:"h5"}}
				subheaderTypographyProps={{variant:"body2"}}
				title={props.title}
				subheader={props.subHeader}/>
			<CardContent>
				<Typography variant="h2" align="center">
					{props.content}
				</Typography>
					{props.component}
				<Typography variant="subtitle2" align="center">
					{props.subContent}
				</Typography>
			</CardContent>
			<CardActions>
				CardActions 
			</CardActions>
		</Card>
	);
}
export default DashboardTile;