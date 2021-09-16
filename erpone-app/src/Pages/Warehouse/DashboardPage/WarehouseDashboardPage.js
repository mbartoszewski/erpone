import { Grid } from '@material-ui/core'
import React from 'react'
import { AreaChart, Bar, BarChart, Area, XAxis, YAxis, LabelList, CartesianGrid, Tooltip, ResponsiveContainer } from 'recharts';
import DashboardTile from '../../../Components/DashboardTile';

const fetchedData = [{ date: "2020/12/31", value: "325744668" },
	{ date: "2021/01//01", value: "325744668" },
	{ date: "2021/01/02", value: "345744668" },
	{ date: "2021/01/03", value: "355744668" },
	{ date: "2021/01/04", value: "225744668" },
	{ date: "2021/01/05", value: "265744668" },
	{ date: "2021/01/06", value: "285744668" },
	{ date: "2021/01/07", value: "245744668" },
	{ date: "2021/01/08", value: "345744668" },
	{ date: "2021/01/09", value: "385744668" },
	{ date: "2021/01/10", value: "375744668" },
	{ date: "2021/01/11", value: "355744668" },
	{ date: "2021/01/12", value: "305744668" },
	{ date: "2021/01/13", value: "295744668" },
]

const WarehouseDashboardPage = () =>
{
	return (
		<div>
			<Grid container spacing={2}>
				<Grid item xl={6} md={6} xs={12}>
					<DashboardTile
						component={<ResponsiveContainer width="100%" height={250}>
							<AreaChart
								data={fetchedData}
								margin={{
									top: 20,
									right: 30,
									left: 20,
									bottom: 5,
								}}
							>
								<CartesianGrid strokeDasharray="3 3" />
								<XAxis dataKey="date" />
								<YAxis />
								<Tooltip />
								<Area type="monotone" dataKey="value" stroke="#e8e8e3" fill="#e8e8e3" />
							</AreaChart>
						</ResponsiveContainer>}
						title={"Warehouse value YTD:"}
						subHeader={"refreshed at 14:55"}/>
				</Grid>
				<Grid item xl={6} md={6} xs={12}>
					<DashboardTile
						component={<ResponsiveContainer width="100%" height={250}>
							<AreaChart
								data={fetchedData}
								margin={{
									top: 20,
									right: 30,
									left: 20,
									bottom: 5,
								}}
							>
								<CartesianGrid strokeDasharray="3 3" />
								<XAxis dataKey="date" />
								<YAxis />
								<Tooltip />
								<Area type="monotone" dataKey="value" stroke="#e8e8e3" fill="#e8e8e3" />
							</AreaChart>
						</ResponsiveContainer>}
						title={"Warehouse value YTD LY:"}
						subHeader={"refreshed at 14:56"}/>
				</Grid>
				<Grid container xs={12} md={12} xl={6} spacing ={2}>
					<Grid item xl={6} md={3} xs={6}>
						<DashboardTile
							title={"Complaint rate this month:"}
							subHeader={"refreshed at 14:44"}
							content={"12.6%"}
							subContent={"-4.87% vs. previous month"} />
					</Grid>
					<Grid item xl={6} md={3} xs={6}>
						<DashboardTile
							title={"Delivery reliability this month:"}
							subHeader={"refreshed at 14:45"}
							content={"87.9%"}
							subContent={"-12.2% vs. previous month"} />
					</Grid>
					<Grid item xl={6} md={3} xs={6}>
						<DashboardTile
							title={"Complaint rate this month:"}
							subHeader={"refreshed at 14:44"}
							content={"12.6%"}
							subContent={"-4.87% vs. previous month"} />
					</Grid>
					<Grid item xl={6} md={3} xs={6}>
						<DashboardTile
							title={"Delivery reliability this month:"}
							subHeader={"refreshed at 14:45"}
							content={"87.9%"}
							subContent={"-12.2% vs. previous month"} />
					</Grid>
				</Grid>
				
				<Grid item xl={6} md={12} xs={12}>
					<DashboardTile
						title={"Inventory value by item group:"}
						subHeader={"refreshed at 12:22"}
						component={<ResponsiveContainer width="100%" height={350}>
							<BarChart
								data={fetchedData}
								margin={{
									top: 5,
									right: 30,
									left: 20,
									bottom: 5,
								}}
								>
								<Bar dataKey="value" barSize={28} fill="#3f6fb5" >
								<LabelList dataKey="value" position="top" />	
								</Bar>
								<CartesianGrid strokeDasharray="3 3" />
								<YAxis />
								<XAxis/>
								 <Tooltip />
							</BarChart>
					</ResponsiveContainer> } />
					</Grid>
			</Grid>
	</div>
)
}

export default WarehouseDashboardPage;