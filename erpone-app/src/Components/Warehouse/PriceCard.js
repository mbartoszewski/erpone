import React, {useContext} from 'react'
import { makeStyles } from '@material-ui/core/styles';
import {
  LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend,
} from 'recharts';
import { useParams } from "react-router-dom";
import moment from 'moment'
import { apiStates, useApi } from '../../Components/Fetch'

const useStyles = makeStyles((theme) => ({
	root: {
		display: 'flex',
		flexDirection: 'column',
		alignItems: 'center',
		'& > *': {
      margin: theme.spacing(1),
    },
  },
}))
  
function PriceCard()
{
	const classes = useStyles();
	const { id } = useParams();
	const currentDate = moment.utc().subtract(6, 'month').format('YYYY-MM-DD');
	const { state, error, data } = useApi(`http://localhost:5000/api/things/${id}/price?startDate=${currentDate}`);
	switch (state)
	{
		case apiStates.ERROR:
		return <p className={classes.root}>Error: {error} || 'General error'</p>;
		case apiStates.SUCCESS:
		return (
			<div className={classes.root}>
				<LineChart width={1400} height={350} data={data} margin={{top: 7, right: 5, left: 5, bottom: 5}}>
					<XAxis dataKey="date"/>
					<YAxis/>
					<CartesianGrid strokeDasharray="3 3"/>
					<Tooltip/>
					<Legend />
					<Line type="monotone" dataKey="price" stroke="#8884d8"/>
				</LineChart>
			</div>	
		);
		default:
		return <p className={classes.errorMsg}>Loading....</p>;
	}	
};
export default PriceCard;