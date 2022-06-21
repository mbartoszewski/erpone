import { Button, Grid, IconButton, TextField, Typography } from '@mui/material'
import React, { useState } from 'react';
import { LineChart, ComposedChart, Bar, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend, ResponsiveContainer, LabelList } from 'recharts';
import DashboardTile from '../../../Components/DashboardTile';
import { useFetch } from '../../../Components/Fetch'
import {ReturnYTD, UnitConverter, VariationCalculator, DocValue, getISODateOfWeek, getISO8601WeekNumberFromDate, groupBy, groupByDate, transposeJson} from '../../../Components/Helpers'
import ArrowForwardIosIcon from '@mui/icons-material/ArrowForwardIos';
import ArrowBackIosNewIcon from '@mui/icons-material/ArrowBackIosNew';
import DeliveriesSheduleTable from '../../../Components/DeliveriesSheduleTable'
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

const PurchaseDashboard = () =>
{
  const [currentWeek, setCurrentWeek] = useState(getISO8601WeekNumberFromDate(new Date()))
  const weekDaysDates = getISODateOfWeek(currentWeek, new Date().getUTCFullYear())

  const [ {data: YTDPurchase}, doYTDPurchase ] = useFetch(`http://localhost:5000/api/documents/value?type=pz&dateFrom=${ReturnYTD(0,0,0,1)}&dateTo=${ReturnYTD(0,0,0,0)}`, null);
  const [ {data: YTDLYPurchase}, doYTDLYPurchase ] = useFetch(`http://localhost:5000/api/documents/value?type=pz&dateFrom=${ReturnYTD(-1, 0, 0, 1)}&dateTo=${ReturnYTD(-1, 0, 0, 0)}`, null);
  const [ {data: futurePurchase}, doFuturePurchase ] = useFetch(`http://localhost:5000/api/documents/value?type=zm&dateFrom=${ReturnYTD(0,0,0,1)}&dateTo=${ReturnYTD(0,0,0,0)}`, null);
  const [{data: deliveriesSheduleData}, doDeliveriesShedule ] = useFetch(null);
  
  const ytdPurchaseMemo = React.useMemo(() => YTDPurchase.data, [YTDPurchase.state]);
  const ytdLyPurchaseMemo = React.useMemo(() => YTDLYPurchase.data, [YTDLYPurchase.state]);
  const futurePurchaseMemo = React.useMemo(() => futurePurchase.data, [futurePurchase.state]);

  const ytdPurchaseValue = DocValue(ytdPurchaseMemo);
  const ytdLyPurchaseValue = DocValue(ytdLyPurchaseMemo);
  const futurePurchaseValue = DocValue(futurePurchaseMemo);
  const [transponedJson, setTransponedJson] = useState()

  React.useMemo(() =>
	{
		if (deliveriesSheduleData.data != null)
    {
      const grouped =  groupByDate(deliveriesSheduleData.data, "targetDateTime")
		  setTransponedJson(transposeJson(grouped))
    } else 
    {
      setTransponedJson([])
      }
	}, [deliveriesSheduleData]);
  
  const handleWeekChange = (increase) =>
  {
   increase === true ? setCurrentWeek(currentWeek +1) : setCurrentWeek(currentWeek -1)
  }

  React.useEffect(() =>
  {
    doDeliveriesShedule({ url: `http://localhost:5000/api/documents/shedule?type=zm&targetDateFrom=${weekDaysDates[0]}&targetDateTo=${weekDaysDates[6]}`, option: null})
  }, [currentWeek])


  const columns = React.useMemo(() => [
    {
      Header: `${weekDaysDates[0]}`, Cell: (cell) =>
      {
        try
        {
          return <TextField
            multiline={true}
            InputProps={{
            readOnly: true,
            }}
            minRows={4}
            size='text'
            fullWidth={true}
            defaultValue={
              cell.row.original[weekDaysDates[0]]['documentStatusEnum'] + '\n'
              + cell.row.original[weekDaysDates[0]]['docNumber'] + '\n'
              + cell.row.original[weekDaysDates[0]]['contractor']['name'] + '\n'
              + cell.row.original[weekDaysDates[0]]['description'] + '\n'
              + cell.row.original[weekDaysDates[0]]['documentDetails'].map((details) =>
          {
           return details.thing.code + ' - ' + details.quantity + ' ' + details.thing.unit.code + '\n'
          })
              }
            />
           
        } catch (err)
        {
          return ""
        }    
    } },
    { 
      Header: `${weekDaysDates[1]}`, Cell: (cell) =>
      {
        try
        {
          return <TextField
            multiline={true}
            InputProps={{
            readOnly: true,
          }}
            minRows={4}
            size='text'
            fullWidth={true}
            defaultValue={
              cell.row.original[weekDaysDates[1]]['documentStatusEnum'] + '\n'
              + cell.row.original[weekDaysDates[1]]['docNumber'] + '\n'
              + cell.row.original[weekDaysDates[1]]['contractor']['name'] + '\n'
              + cell.row.original[weekDaysDates[1]]['description'] + '\n'
              + cell.row.original[weekDaysDates[1]]['documentDetails'].map((details) =>
          {
           return details.thing.code + ' - ' + details.quantity + ' ' + details.thing.unit.code + '\n'
          })
              }
            />
           
        } catch (err)
        {
          return ""
        }    
    }  },
    { 
      Header: `${weekDaysDates[2]}`, Cell: (cell) =>
      {
        try
        {
          return <TextField
            multiline={true}
            InputProps={{
            readOnly: true,
          }}
            minRows={4}
            size='text'
            fullWidth={true}
            defaultValue={
              cell.row.original[weekDaysDates[2]]['documentStatusEnum'] + '\n'
              + cell.row.original[weekDaysDates[2]]['docNumber'] + '\n'
              + cell.row.original[weekDaysDates[2]]['contractor']['name'] + '\n'
              + cell.row.original[weekDaysDates[2]]['description'] + '\n'
              + cell.row.original[weekDaysDates[2]]['documentDetails'].map((details) =>
          {
           return details.thing.code + ' - ' + details.quantity + ' ' + details.thing.unit.code + '\n'
          })
              }
            />
           
        } catch (err)
        {
          return ""
        }    
    }  },
    { 
      Header: `${weekDaysDates[3]}`, Cell: (cell) =>
      {
        try
        {
          return <TextField
            multiline={true}
            InputProps={{
            readOnly: true,
          }}
            minRows={4}
            size='text'
            fullWidth={true}
            defaultValue={
              cell.row.original[weekDaysDates[3]]['documentStatusEnum'] + '\n'
              + cell.row.original[weekDaysDates[3]]['docNumber'] + '\n'
              + cell.row.original[weekDaysDates[3]]['contractor']['name'] + '\n'
              + cell.row.original[weekDaysDates[3]]['description'] + '\n'
              + cell.row.original[weekDaysDates[3]]['documentDetails'].map((details) =>
          {
           return details.thing.code + ' - ' + details.quantity + ' ' + details.thing.unit.code + '\n'
          })
              }
            />
           
        } catch (err)
        {
          return ""
        }    
    }  },
    { 
      Header: `${weekDaysDates[4]}`, Cell: (cell) =>
      {
        try
        {
          return <TextField
            multiline={true}
            InputProps={{
            readOnly: true,
          }}
            minRows={4}
            size='text'
            fullWidth={true}
            defaultValue={
              cell.row.original[weekDaysDates[4]]['documentStatusEnum'] + '\n'
              + cell.row.original[weekDaysDates[4]]['docNumber'] + '\n'
              + cell.row.original[weekDaysDates[4]]['contractor']['name'] + '\n'
              + cell.row.original[weekDaysDates[4]]['description'] + '\n'
              + cell.row.original[weekDaysDates[4]]['documentDetails'].map((details) =>
          {
           return details.thing.code + ' - ' + details.quantity + ' ' + details.thing.unit.code + '\n'
          })
              }
            />
           
        } catch (err)
        {
          return ""
        }    
    }  },
    { 
      Header: `${weekDaysDates[5]}`, Cell: (cell) =>
      {
        try
        {
          return <TextField
            multiline={true}
            InputProps={{
            readOnly: true,
          }}
            minRows={4}
            size='text'
            fullWidth={true}
            defaultValue={
              cell.row.original[weekDaysDates[5]]['documentStatusEnum'] + '\n'
              + cell.row.original[weekDaysDates[5]]['docNumber'] + '\n'
              + cell.row.original[weekDaysDates[5]]['contractor']['name'] + '\n'
              + cell.row.original[weekDaysDates[5]]['description'] + '\n'
              + cell.row.original[weekDaysDates[5]]['documentDetails'].map((details) =>
          {
           return details.thing.code + ' - ' + details.quantity + ' ' + details.thing.unit.code + '\n'
          })
              }
            />
           
        } catch (err)
        {
          return ""
        }    
    }  },
    { 
      Header: `${weekDaysDates[6]}`, Cell: (cell) =>
      {
        try
        {
          return <TextField
            multiline={true}
            InputProps={{
            readOnly: true,
          }}
            minRows={4}
            size='text'
            fullWidth={true}
            defaultValue={
              cell.row.original[weekDaysDates[6]]['documentStatusEnum'] + '\n'
              + cell.row.original[weekDaysDates[6]]['docNumber'] + '\n'
              + cell.row.original[weekDaysDates[6]]['contractor']['name'] + '\n'
              + cell.row.original[weekDaysDates[6]]['description'] + '\n'
              + cell.row.original[weekDaysDates[6]]['documentDetails'].map((details) =>
          {
           return details.thing.code + ' - ' + details.quantity + ' ' + details.thing.unit.code + '\n'
          })
              }
            />
           
        } catch (err)
        {
          return ""
        }    
    }  },], [currentWeek]);
  
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
            content={"do zrobienia"}
          />
        </Grid>
        <Grid item xs={6} md={2} xl={2}>
          <DashboardTile
            title={"Overdue customers payments"}
            subHeader={"refreshed at 11:57"}
            content={"do zrobienia"}
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
            component={<DeliveriesSheduleTable
              data={transponedJson != undefined && transponedJson != null ? transponedJson : [0]}
              columns={columns}
              manual
              detailLink={"purchase/documents/details"}
            />}
            action={<Grid container spacing ={4}>
                <Grid item xs={2} xm={2} xl={2}>
                        <IconButton
                        color='inherit'
                        onClick={() => { handleWeekChange(false) }}>
                          <ArrowBackIosNewIcon/>
                        </IconButton>
                      </Grid>
                      <Grid item xs={2} xm={2} xl={2}>
                        <Typography variant="h4" align="center">
                            {currentWeek}
                        </Typography>
                      </Grid>
                      <Grid item xs={2} xm={2} xl={2}>
                        <IconButton
                          color='inherit'
                          onClick={() => { handleWeekChange(true) }}>
                          <ArrowForwardIosIcon />
                        </IconButton>
                      </Grid>
                      <Grid item xs={6} xm={6} xl={6}>
                          <Typography variant="h7" align="center">
                              {weekDaysDates[0]} - {weekDaysDates[6]}
                          </Typography>
                        </Grid>
                  </Grid>} />
        </Grid>
		</Grid>
    </div>
);
}

export default PurchaseDashboard;