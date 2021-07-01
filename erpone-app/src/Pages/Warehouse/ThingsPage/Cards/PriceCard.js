import React from 'react'
import { makeStyles } from '@material-ui/core/styles';
import {
  LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend, ResponsiveContainer
} from 'recharts';
import { useParams } from "react-router-dom";
import moment from 'moment'
import { apiStates, useApi } from '../../../../Components/Fetch'

const useStyles = makeStyles((theme) => ({
	root: {
		width: "100%",
		height: 350,
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

	const salesPrice = (data) =>
	{
		if (data.documentsDetails != null)
			{
				if (data.documentsDetails.document.documentTypeEnum === "rw" || data.documentsDetails.document.documentTypeEnum === "wz" || data.documentsDetails.document.documentTypeEnum === "wzz")
				{
					return data.price
				}
			}
	}

	const purchasePrice = (data) =>
	{
		if (data.documentsDetails != null)
			{
				if (data.documentsDetails.document.documentTypeEnum === "zm" || data.documentsDetails.document.documentTypeEnum === "pw" || data.documentsDetails.document.documentTypeEnum === "pz")
				{
					return data.price
				}
			}
	}
	const dateTimeFormated = (data) =>
	{
		return moment(data.date).format('YYYY-MM-DD') 
		}
	switch (state)
	{
		case apiStates.ERROR:
		case apiStates.EMPTY:
			return <p className={classes.root}>Error: {error} || 'General error'</p>;
		case apiStates.SUCCESS:
			return (
				<div className={classes.root} >
				<ResponsiveContainer>  
					<LineChart width={850} height={350} data={data} margin={{top: 7, right: 2, left: 2, bottom: 5}}>
							<XAxis dataKey={dateTimeFormated}/>
							<YAxis/>
							<CartesianGrid strokeDasharray="3 3"/>
							<Tooltip />
							<Legend/>
							<Line connectNulls type="monotype" dataKey={salesPrice} name="Sale price" stroke="#8884d8" activeDot={{ r: 8 }}/>
							<Line connectNulls type="monotype" dataKey={purchasePrice} name = "Purchase price" stroke="#82ca9d" activeDot={{ r: 8 }}/>
					</LineChart>
				</ResponsiveContainer>	
			</div>	
		);
		default:
		return <p className={classes.errorMsg}>Loading....</p>;
	}	
};
export default PriceCard;