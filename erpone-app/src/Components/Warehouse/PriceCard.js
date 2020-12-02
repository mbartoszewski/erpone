import React from 'react'
import { makeStyles } from '@material-ui/core/styles';
import Paper from '@material-ui/core/Paper';
import {
  LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend,
} from 'recharts';

const useStyles = makeStyles((theme) => ({
	root: {
		display: 'flex',
		alignItems: 'center',
		justifyContent: 'center'
  },
}))
function PriceCard()
{
	const classes = useStyles();
const data = [
      {name: '2020-01-01', uv: 4000, pv: 2400, amt: 2400},
      {name: '2020-02-01', uv: 3000, pv: 1398, amt: 2210},
      {name: '2020-03-01', uv: 2000, pv: 9800, amt: 2290},
      {name: '2020-04-01', uv: 2780, pv: 3908, amt: 2000},
      {name: '2020-05-01', uv: 1890, pv: 4800, amt: 2181},
      {name: '2020-06-01', uv: 2390, pv: 3800, amt: 2500},
      {name: '2020-07-01', uv: 3490, pv: 4300, amt: 2100},
];
	return (
		<div className={classes.root}>
					<LineChart width={900} height={350} data={data}
           				 margin={{top: 7, right: 10, left: 10, bottom: 5}}>
						<XAxis dataKey="name"/>
						<YAxis/>
						<CartesianGrid strokeDasharray="3 3"/>
						<Tooltip/>
						<Legend />
						<Line type="monotone" dataKey="pv" stroke="#8884d8" activeDot={{r: 8}}/>
						<Line type="monotone" dataKey="uv" stroke="#82ca9d" />
					</LineChart>		
		</div>
	
	);
};
export default PriceCard;