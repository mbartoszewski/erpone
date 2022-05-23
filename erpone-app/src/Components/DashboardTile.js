import React from "react";
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import Typography from '@mui/material/Typography';
import CardHeader from '@mui/material/CardHeader';

const DashboardTile = (props) =>
{
	
	return (
		<Card sx= {{backgroundColor: "#f4f4f4"}}>
			<CardHeader
				titleTypographyProps={{ variant: "h5" }}
				subheaderTypographyProps={{ variant: "body2" }}
				title={props.title}
				subheader={props.subHeader}
				action={props.action} />
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