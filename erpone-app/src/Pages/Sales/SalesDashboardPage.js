import { Grid, Typography } from '@material-ui/core'
import React from 'react'
import { makeStyles } from '@material-ui/styles'
import DashboardTile from '../../Components/DashboardTile';
import { LineChart, BarChart, Bar, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend, ResponsiveContainer, LabelList } from 'recharts';
import ReactDOM from 'react-dom'
const useStyles = makeStyles((theme) => ({
}));

const data = [
  {
    date: '2021-01',
    name: 'test1',
    de: 500,
    pv: 2400,
    amt: 2400,
  },
  {
    date: '2021-02',
    name: 'test2',
    de: 870,
    pv: 1398,
    amt: 2210,
  },
  {
    date: '2021-03',
    name: 'test3',
    de: 98,
    pv: 9800,
    amt: 4190,
  },
  {
    date: '2021-04',
    name: 'test4',
    pv: 3908,
    amt: 4300,
  },
  {
    date: '2021-05',
    name: 'test5',
    pv: 4800,
    amt: 4581,
  },
  {
    date: '2021-06',
    name: 'test6',
    pv: 3800,
    amt: 5400,
  },
  {
    date: '2021-07',
    name: 'test7',
    pv: 4300,
    amt: 5400,
  },
  {
    date: '2021-08',
    name: 'test8',
    pv: 1200,
    amt: 3100,
  },
  {
    date: '2021-09',
    name: 'test9',
    pv: 1920,
    amt: 4100,
  },
  {
    date: '2021-10',
    name: 'test10',
    pv: 4300,
    amt: 3100,
  },
  {
    date: '2021-11',
    name: 'test11',
    pv: 4900,
    amt: 2100,
  },
  {
    date: '2021-12',
    name: 'test12',
    pv: 300,
    amt: 1100,
  },
];

const SalesDashboardPage = () =>
{
  const classes = useStyles();
 
	return (
    <div>
			<Grid container spacing={2}>
				<Grid item xl={4} md={4} xs={12}>
					<DashboardTile title="Sales Dashboard" subHeader="refreshed at 19:22" content="????"/>
				</Grid>
				<Grid item xl={2} md={2} xs={6}>
					<DashboardTile title="Sales value YTD:" subHeader="refreshed at 19:22" content="2.74 mln €" subContent="+7,87% vs. LY"/>
				</Grid>
				<Grid item xl={2} md={2} xs={6}>
					<DashboardTile title="Sales value YTD LY:" subHeader="refreshed at 19:23" content="2.52 mln €"/>
				</Grid>
				<Grid item xl={2} md={2} xs={6}>
					<DashboardTile title="Budget realization in % YTD:" subHeader="refreshed at 17:54" content="85%"/>
				</Grid>
				<Grid item xl={2} md={2} xs={6}>
					<DashboardTile title="Budget realization in % YTD LY:" subHeader="refreshed at 17:55" content="78%"/>
				</Grid>
				<Grid item xl={4} md={4} xs={12}>
					<DashboardTile title="Sales summary YTD:" subHeader="refreshed at 16:55" content="Tabela z ilością sprzedanych towarów w każdym dniu, obok porównanie miesiąc do miesiąca"/>
				</Grid>
				<Grid item xl={4} md={4} xs={12}>
					<DashboardTile title="Sales summary vs. budget monthly:"
						subHeader="refreshed at 16:57"
						component={<ResponsiveContainer width="100%" height={450}>
								<LineChart
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
								<Line dataKey="pv" strokeWidth={4} name="Budget" type="monotone" stroke="#3f6fb5" />
								<Line dataKey="amt" strokeWidth={4} name="Sales" type="monotone" stroke="#77dd77" />
							</LineChart>
							</ResponsiveContainer>}/>
				</Grid>
				<Grid item xl={4} md={4} xs={12}>
					<DashboardTile
						title={"Sales summary by customer:"}
						subHeader={"refreshed at 12:22"}
						component={<ResponsiveContainer width="100%" height={450}>
							<BarChart
								data={data}
								layout="vertical"
								margin={{
									top: 5,
									right: 30,
									left: 20,
									bottom: 5,
								}}
								>
								<Bar dataKey="amt" barSize={20} fill="#3f6fb5" />
								<CartesianGrid strokeDasharray="3 3" />
								<YAxis dataKey="name" type="category" />
								<XAxis type="number"/>
								 <Tooltip />
							</BarChart>
					</ResponsiveContainer> } />
				</Grid>
			</Grid>
	</div>
)
}

export default SalesDashboardPage;