import React from 'react'
import {
  LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend, ResponsiveContainer
} from 'recharts';
import { useParams } from "react-router-dom";
import moment from 'moment'
import { apiStates, useFetch } from '../../../../Components/Fetch'

const errorMsg =
{
  position: 'absolute',
  top: '50%',
  left: '50%'
}
  

function PriceCard()
{
	const { id } = useParams();
	const currentDate = moment.utc().subtract(6, 'month').format('YYYY-MM-DD');
	const [{data: priceCardDetails}, doPriceCArdDetails] = useFetch(`http://localhost:5000/api/things/${id}/price?startDate=${currentDate}`);

	const salesPrice = (data) =>
	{
		if (data !== null)
			{
				if (data.documentsDetails.document.documentTypeEnum === "rw" || data.documentsDetails.document.documentTypeEnum === "wz" || data.documentsDetails.document.documentTypeEnum === "wzz")
				{
					return data.detailPrice
				}
			}
	}

	const purchasePrice = (data) =>
	{
		if (data !== null)
			{
				if (data.documentsDetails.document.documentTypeEnum === "zm" || data.documentsDetails.document.documentTypeEnum === "pw" || data.documentsDetails.document.documentTypeEnum === "pz")
				{
					return data.detailPrice
				}
			}
	}
	const dateTimeFormated = (data) =>
	{
		return moment(data.date).format('YYYY-MM-DD') 
	}
	
	switch (priceCardDetails.state)
	{
		case apiStates.ERROR:
		case apiStates.EMPTY:
			return <p sx={errorMsg}>Error: {priceCardDetails.error} || 'General error'</p>;
		case apiStates.SUCCESS:
			return (
				<ResponsiveContainer>  
					<LineChart width="100%" height={250} data={priceCardDetails.data} margin={{top: 7, right: 2, left: 2, bottom: 5}}>
							<XAxis dataKey={dateTimeFormated}/>
							<YAxis/>
							<CartesianGrid strokeDasharray="3 3"/>
							<Tooltip />
							<Legend/>
							<Line connectNulls type="monotype" dataKey={salesPrice} name="Sale price" stroke="#8884d8" activeDot={{ r: 8 }}/>
							<Line connectNulls type="monotype" dataKey={purchasePrice} name = "Purchase price" stroke="#82ca9d" activeDot={{ r: 8 }}/>
					</LineChart>
				</ResponsiveContainer>	
		);
		default:
		return <p sx={errorMsg}>Loading....</p>;
	}	
};
export default PriceCard;