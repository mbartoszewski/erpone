import { Grid } from '@material-ui/core'
import React from 'react'
import { BarChart, Bar, Cell, XAxis, YAxis, CartesianGrid, Tooltip, Legend, ResponsiveContainer } from 'recharts';
const data = [
  {
    name: 'Page A',
    uv: 4000,
    pv: 2400,
    amt: 2400,
  },
  {
    name: 'Page B',
    uv: 3000,
    pv: 1398,
    amt: 2210,
  },
  {
    name: 'Page C',
    uv: 2000,
    pv: 9800,
    amt: 2290,
  },
  {
    name: 'Page D',
    uv: 2780,
    pv: 3908,
    amt: 2000,
  },
  {
    name: 'Page E',
    uv: 1890,
    pv: 4800,
    amt: 2181,
  },
  {
    name: 'Page F',
    uv: 2390,
    pv: 3800,
    amt: 2500,
  },
  {
    name: 'Page G',
    uv: 3490,
    pv: 4300,
    amt: 2100,
  },
];
const PurchaseDashboard = () =>
{
	return (<div>
		<Grid container xs={12}>
			<Grid item xs={12} sm={12} xl={12}>
				<div style={{backgroundColor:"green", height:"330px"}}>
					<p>Pierwszy grid z wykresem kołowym - podliczenie kasy na magazynie z podziałem na kategorie surowcowe</p>
				</div>
			</Grid>
			<Grid item xs={12} sm={6} xl={6}>
				<ResponsiveContainer width="100%" height="100%">
					<BarChart
						width={500}
						height={300}
						data={data}
						margin={{
							top: 20,
							right: 30,
							left: 20,
							bottom: 5,
						}}
					>
						<CartesianGrid strokeDasharray="3 3" />
						<XAxis dataKey="name" />
						<YAxis />
						<Tooltip />
						<Legend />
						<Bar dataKey="pv" stackId="a" fill="#8884d8" />
						<Bar dataKey="uv" stackId="a" fill="#82ca9d" />
						</BarChart>
				</ResponsiveContainer>
			</Grid>
			<Grid item xs={12} sm={6} xl={6}>
				<div style={{backgroundColor:"blue", height:"280px"}}>
					<p>Podsumowanie zleceń do uszykowania na produkcję</p>
				</div>
			</Grid>
			<Grid item xs={12} sm={12} xl={12}>
				<div style={{backgroundColor:"grey", height:"330px"}}>
					<p>Widok tygodnia z harmonogramem dostaw</p>
				</div>
			</Grid>
		</Grid>
	</div>);
}

export default PurchaseDashboard;