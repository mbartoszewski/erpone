import { Divider, Grid } from '@mui/material'
import React, { useState } from 'react';
import { LineChart, ComposedChart, Bar, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend, ResponsiveContainer, LabelList } from 'recharts';
import DashboardTile from '../../../Components/DashboardTile';
import { apiStates, useApi } from '../../../Components/Fetch'
import {ReturnYTD, UnitConverter, VariationCalculator, DocValue} from '../../../Components/Helpers'

const dummy = [
  {
    date: '2021-01',
    uv: 0,
    de: 500,
    pv: 2400,
    amt: 2400,
  },
  {
    date: '2021-02',
    uv: 0,
    de: 870,
    pv: 1398,
    amt: 2210,
  },
  {
    date: '2021-03',
    uv: 0,
    de: 98,
    pv: 9800,
    amt: 4190,
  },
  {
    date: '2021-04',
    uv: 2780,
    pv: 3908,
    amt: 4300,
  },
  {
    date: '2021-05',
    uv: 1890,
    pv: 4800,
    amt: 4581,
  },
  {
    date: '2021-06',
    uv: 2390,
    pv: 3800,
    amt: 5400,
  },
  {
    date: '2021-07',
    uv: 3490,
    pv: 4300,
    amt: 5400,
  },
  {
    date: '2021-08',
    uv: 3290,
    pv: 1200,
    amt: 3100,
  },
  {
    date: '2021-09',
    uv: 3220,
    pv: 1920,
    amt: 4100,
  },
  {
    date: '2021-10',
    uv: 90,
    pv: 4300,
    amt: 3100,
  },
  {
    date: '2021-11',
    uv: 190,
    pv: 4900,
    amt: 2100,
  },
  {
    date: '2021-12',
    uv: 390,
    pv: 300,
    amt: 1100,
  },
];

const names = [
  'Oliver Hansen',
  'Van Henry',
  'April Tucker',
  'Ralph Hubbard',
  'Omar Alexander',
  'Carlos Abbott',
  'Miriam Wagner',
  'Bradley Wilkerson',
  'Virginia Andrews',
  'Kelly Snyder',
];

const PurchaseDashboard = () =>
{
  const { state: YTDPurchaseState, error: YTDPurchaseError, data: YTDPurchaseData } = useApi(`http://localhost:5000/api/documents/value?type=pz&dateFrom=${ReturnYTD(0,0,0,1)}&dateTo=${ReturnYTD(0,0,0,0)}`);
  const { state: YTDLYPurchaseState, error: YTDLYPurchaseError, data: YTDLYPurchaseData } = useApi(`http://localhost:5000/api/documents/value?type=pz&dateFrom=${ReturnYTD(-1, 0, 0, 1)}&dateTo=${ReturnYTD(-1, 0, 0, 0)}`);
  const { state: futurePurchaseState, error: futurePurchaseError, data: futurePurchaseData } = useApi(`http://localhost:5000/api/documents/value?type=zm&dateFrom=${ReturnYTD(0,0,0,1)}&dateTo=${ReturnYTD(0,0,0,0)}`);

  const ytdPurchase = React.useMemo(() => YTDPurchaseData, YTDPurchaseState);
  const ytdLyPurchase = React.useMemo(() => YTDLYPurchaseData, YTDLYPurchaseState);
  const futurePurchase = React.useMemo(() => futurePurchaseData, futurePurchaseState);

  const ytdPurchaseValue = DocValue(ytdPurchase);
  const ytdLyPurchaseValue = DocValue(ytdLyPurchase);
  const futurePurchaseValue = DocValue(futurePurchase);
 
  return (
    <div >
      <Grid container direction="row" spacing={2}>
        <Grid item xs={6} md={3} xl={3}>
          <DashboardTile
            title={"Purchase value YTD:"}
            subHeader={"refreshed at 11:55"}
            content={UnitConverter(ytdPurchaseValue)}
            subContent={VariationCalculator(ytdPurchaseValue, ytdLyPurchaseValue) + "% vs. LY"}
          />
        </Grid>
        <Grid item xs={6} md={3} xl={3}>
          <DashboardTile
            title={"Purchase value YTD LY:"}
            subHeader={"refreshed at 11:55"}
            content={UnitConverter(ytdLyPurchaseValue)}
          />
        </Grid>
        <Grid item xs={6} md={2} xl={2}>
          <DashboardTile
            title={"Purchase value till end of year:"}
            subHeader={"refreshed at 11:55"}
            content={UnitConverter(futurePurchaseValue)}
          />
        </Grid>
        <Grid item xs={6} md={2} xl={2}>
          <DashboardTile
            title={"Overdue suppliers payments"}
            subHeader={"refreshed at 11:57"}
            content={"200 tys. €"}
          />
        </Grid>
        <Grid item xs={6} md={2} xl={2}>
          <DashboardTile
            title={"Overdue customers payments"}
            subHeader={"refreshed at 11:57"}
            content={"470 tys. €"}
          />
        </Grid>
        <Grid item xs={12} md={6} xl={6}>
          <DashboardTile
            title={"Cashflow:"}
            subHeader = {"refreshed at 15:21"}
            component={<ResponsiveContainer width="100%" height={450}>
              <ComposedChart
                data={dummy}
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
                <Legend />
                <Bar dataKey="pv" name="Paid invoices" stackId="a" fill="#d3d9dd" />
                <Bar dataKey="de" name="Delayed" stackId="a" fill="#ff6961"></Bar>
                <Bar dataKey="uv" name="To pay" stackId="a" fill="#3f6fb5">
                  <LabelList content={dummy.pv + dummy.uv} position="top" />
                </Bar>
                <Line dataKey="amt" strokeWidth={4} name="Budget" type="monotone" stroke="#ff7300" />
              </ComposedChart>
            </ResponsiveContainer>}
          />
        </Grid>
        <Grid item xs={12} md={6} xl={6}>
          <DashboardTile
            title={"Cash balance:"}
            subHeader = {"refreshed at 15:21"}
            component={<ResponsiveContainer width="100%" height={450}>
              <LineChart
                data={dummy}
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
                <Legend />
                <Line dataKey="pv" strokeWidth={4} name="Liabilities" type="monotone" stroke="#ff6961" />
                <Line dataKey="amt" strokeWidth={4} name="Receivables" type="monotone" stroke="#77dd77" />
              </LineChart>
            </ResponsiveContainer>}
          />
        </Grid>
        <Grid item xs={12} md={12} xl={12}>
          <DashboardTile
            title={"Deliveries:"}
            subHeader={"refreshed at 15:47"}
            content={ "Weekly deliveries scheduler."}/>
        </Grid>
		</Grid>
    </div>
);
}

export default PurchaseDashboard;