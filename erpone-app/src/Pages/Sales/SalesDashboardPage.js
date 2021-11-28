import { Grid} from '@mui/material'
import React from 'react'
import DashboardTile from '../../Components/DashboardTile';
import { LineChart, BarChart, Bar, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend, ResponsiveContainer} from 'recharts';
import { ReturnYTD, UnitConverter, VariationCalculator, DocValue, DocValueByProperties } from '../../Components/Helpers'
import { apiStates, useApi } from '../../Components/Fetch'

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
  const { state: YTDSalesState, error: YTDSalesError, data: YTDSalesData } = useApi(`http://localhost:5000/api/documents/value?type=wz&dateFrom=${ReturnYTD(0,0,0,1)}&dateTo=${ReturnYTD(0,0,0,0)}`);
  const { state: YTDLYSalesState, error: YTDLYSalesError, data: YTDLYSalesData } = useApi(`http://localhost:5000/api/documents/value?type=wz&dateFrom=${ReturnYTD(-1, 0, 0, 1)}&dateTo=${ReturnYTD(-1, 0, 0, 0)}`);
  const { state: futureSalesState, error: futureSalesError, data: futureSalesData } = useApi(`http://localhost:5000/api/documents/value?type=zm&dateFrom=${ReturnYTD(0,0,0,1)}&dateTo=${ReturnYTD(0,0,0,0)}`);

  const ytdSales = React.useMemo(() => YTDSalesData, YTDSalesState);
  const ytdLySales = React.useMemo(() => YTDLYSalesData, YTDLYSalesState);
  const futureSales = React.useMemo(() => futureSalesData, futureSalesState);

  const ytdSalesValue = DocValue(ytdSales);
  const ytdLySalesValue = DocValue(ytdLySales);
  const futureSalesValue = DocValue(futureSales);
  const YTDSalesValueByCustomer = DocValueByProperties(ytdSales, 'contractor');
	return (
    <div>
			<Grid container spacing={2}>
				<Grid item xl={4} md={4} xs={12}>
          <DashboardTile
            title="Sales Dashboard"
            subHeader="refreshed at 19:22"
            content={"?>"} />
				</Grid>
				<Grid item xl={2} md={2} xs={6}>
          <DashboardTile
            title="Sales value YTD:"
            subHeader="refreshed at 19:22"
            content={UnitConverter(ytdSalesValue)}
            subContent={VariationCalculator(ytdSalesValue, ytdLySalesValue) + "% vs. LY"} />
				</Grid>
				<Grid item xl={2} md={2} xs={6}>
          <DashboardTile
            title="Sales value YTD LY:"
            subHeader="refreshed at 19:23"
            content={UnitConverter(ytdLySalesValue)} />
				</Grid>
				<Grid item xl={2} md={2} xs={6}>
          <DashboardTile
            title="Budget realization in % YTD:"
            subHeader="refreshed at 17:54"
            content="85%" />
				</Grid>
				<Grid item xl={2} md={2} xs={6}>
          <DashboardTile
            title="Budget realization in % YTD LY:"
            subHeader="refreshed at 17:55"
            content="78%" />
				</Grid>
				<Grid item xl={4} md={4} xs={12}>
          <DashboardTile
            title="Sales summary YTD:"
            subHeader="refreshed at 16:55"
            content="Table with sales quantity, value by item " />
				</Grid>
				<Grid item xl={4} md={4} xs={12}>
          <DashboardTile
            title="Sales summary vs. budget monthly:"
						subHeader="refreshed at 16:57"
						component={<ResponsiveContainer width="100%" height={350}>
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
						component={<ResponsiveContainer width="100%" height={350}>
							<BarChart
								data={YTDSalesValueByCustomer}
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