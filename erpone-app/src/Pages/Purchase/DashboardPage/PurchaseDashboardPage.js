import { Divider, Grid } from '@material-ui/core'
import React, { useState } from 'react';
import { makeStyles } from '@material-ui/core/styles'
import TextField from '@material-ui/core/TextField';
import Input from '@material-ui/core/Input';
import InputLabel from '@material-ui/core/InputLabel';
import MenuItem from '@material-ui/core/MenuItem'
import FormControl from '@material-ui/core/FormControl';
import ListItemText from '@material-ui/core/ListItemText';
import Select from '@material-ui/core/Select';
import Checkbox from '@material-ui/core/Checkbox';
import MuiFormControl from '@material-ui/core/FormControl';
import Chip from '@material-ui/core/Chip';
import { LineChart, Sector, ComposedChart, Bar, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend, ResponsiveContainer, LabelList } from 'recharts';

const useStyles = makeStyles((theme) => ({
  root: {flexGrow: 1,},
  formControl: {
    margin: theme.spacing(1),
    minWidth: 150,
    maxWidth: 480,
    paddingRight: 2,
  },
   chips: {
    display: 'flex',
    flexWrap: 'wrap',
  },
  chip: {
    margin: 2,
  },
}));

const data = [
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
const data02 = [
  { name: 'kartony', value: 100 },
  { name: 'folie', value: 300 },
  { name: 'części zamienne', value: 100 },
  { name: 'B2', value: 80 },
  { name: 'B3', value: 40 },
  { name: 'B4', value: 30 },
  { name: 'B5', value: 50 },
  { name: 'C1', value: 100 },
  { name: 'C2', value: 200 },
  { name: 'D1', value: 150 },
  { name: 'D2', value: 50 },
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
const renderActiveShape = (props) => {
  const RADIAN = Math.PI / 180;
  const { cx, cy, midAngle, innerRadius, outerRadius, startAngle, endAngle, fill, payload, percent, value, name } = props;
  const sin = Math.sin(-RADIAN * midAngle);
  const cos = Math.cos(-RADIAN * midAngle);
  const sx = cx + (outerRadius + 10) * cos;
  const sy = cy + (outerRadius + 10) * sin;
  const mx = cx + (outerRadius + 30) * cos;
  const my = cy + (outerRadius + 30) * sin;
  const ex = mx + (cos >= 0 ? 1 : -1) * 22;
  const ey = my;
  const textAnchor = cos >= 0 ? 'start' : 'end';

  return (
    <g>
      <text x={cx} y={cy} dy={8} textAnchor="middle" fill={fill}>
        {payload.name}
      </text>
      <Sector
        cx={cx}
        cy={cy}
        innerRadius={innerRadius}
        outerRadius={outerRadius}
        startAngle={startAngle}
        endAngle={endAngle}
        fill={fill}
      />
      <Sector
        cx={cx}
        cy={cy}
        startAngle={startAngle}
        endAngle={endAngle}
        innerRadius={outerRadius + 6}
        outerRadius={outerRadius + 10}
        fill={fill}
      />
      <path d={`M${sx},${sy}L${mx},${my}L${ex},${ey}`} stroke={fill} fill="none" />
      <circle cx={ex} cy={ey} r={2} fill={fill} stroke="none" />
      <text x={ex + (cos >= 0 ? 1 : -1) * 12} y={ey} textAnchor={textAnchor} fill="#333">{`${name}: ${value}`}</text>
      <text x={ex + (cos >= 0 ? 1 : -1) * 12} y={ey} dy={18} textAnchor={textAnchor} fill="#999">
        {`(Rate ${(percent * 100).toFixed(2)}%)`}
      </text>
    </g>
  );
};

const PurchaseDashboard = () =>
{
  const classes = useStyles();
  const [activeindex, setActiveIndex] = useState(0)
  const [supplierName, setSupplierName] = useState([]);
  const [category, setCategory] = useState([]);
  const [rawMaterial, setRawMaterial] = useState([]);
  const fetchedData = React.useMemo(() => data, data);
 
  const handleSupplierChange = (event) =>{
    setSupplierName(event.target.value);
  };
   const handleCategoryChange = (event) =>{
    setCategory(event.target.value);
  };
   const handleRawMaterialChange = (event) =>{
    setRawMaterial(event.target.value);
  };
  const onPieEnter = (_, index) => {
   setActiveIndex(index);
  };

  return (
    <div className={classes.root}>
      <Grid container direction="row" spacing={2}>
            <Grid item xs={12} md={12} xl={12}>
              <p>
                Wartość magazynu: 596 566.98zł
              </p>
              <Divider/>
              </Grid>
      </Grid>

      <Grid container direction="row" spacing={2}>
        <Grid item xs='auto' md='auto' xl='auto'>
          <FormControl className={classes.formControl}>
            <TextField
              id="fromDate"
              label="From:"
              type="date"
              InputLabelProps={{
                shrink: true,
              }} />
          </FormControl>
          </Grid>
        <Grid item xs='auto' md='auto' xl='auto'>
          <MuiFormControl className={classes.formControl}>
            <TextField
              id="toDate"
              label="To:"
              type="date"
              InputLabelProps={{
                shrink: true,
              }} />
          </MuiFormControl>
          </Grid>
        <Grid item xs='auto' md='auto' xl='auto'>
          <FormControl size='small' className={classes.formControl}>
            <InputLabel id="category-multiple-checkbox-label">Category:</InputLabel>
            <Select
              SelectDisplayProps={{ style: { height: 'auto' } }}
              labelId="category-multiple-checkbox-label"
              id="category-multiple-checkbox-select"
              multiple
              value={category}
              onChange={handleCategoryChange}
              input={<Input />}
              renderValue={(selected) => (<div className={classes.chips}>
                {selected.map((value) => (
                  <Chip key={value} label={value} className={classes.chip} />
                ))}
              </div>)}>
              {names.map((n) => (
                <MenuItem key={n} value={n}>
                  <Checkbox checked={category.indexOf(n) > -1} />
                  <ListItemText primary={n}/>
                  </MenuItem>
                ))}
            </Select>
          </FormControl>
          </Grid>
          <Grid item xs='auto' md='auto' xl='auto'>
              <MuiFormControl size='small' className={classes.formControl}>
              <InputLabel id="supplier-multiple-checkbox-label">Supplier:</InputLabel>
              <Select
                SelectDisplayProps={{ style: { height: 'auto' } }}
                labelId="supplier-multiple-checkbox-label"
                id="supplier-multiple-checkbox-select"
                multiple
                value={supplierName}
                onChange={handleSupplierChange}
                input={<Input />}
                renderValue={(selected) => (<div className={classes.chips}>
                  {selected.map((value) => (
                    <Chip key={value} label={value} className={classes.chip} />
                  ))}
                </div>)}>
                {names.map((n) => (
                  <MenuItem key={n} value={n}>
                    <Checkbox checked={supplierName.indexOf(n) > -1} />
                    <ListItemText primary={n}/>
                    </MenuItem>
                  ))}
              </Select>
            </MuiFormControl>
            </Grid>
            <Grid item xs='auto' md='auto' xl='auto'>
              <FormControl size='small' className={classes.formControl}>
              <InputLabel id="material-multiple-checkbox-label">Material:</InputLabel>
              <Select
                SelectDisplayProps={{ style: { height: 'auto' } }}
                labelId="material-multiple-checkbox-label"
                id="material-multiple-checkbox-select"
                multiple
                value={rawMaterial}
                onChange={handleRawMaterialChange}
                input={<Input />}
                renderValue={(selected) => (<div className={classes.chips}>
                  {selected.map((value) => (
                    <Chip key={value} label={value} className={classes.chip} />
                  ))}
                </div>)}>
                {names.map((n) => (
                  <MenuItem key={n} value={n}>
                    <Checkbox checked={rawMaterial.indexOf(n) > -1} />
                    <ListItemText primary={n}/>
                    </MenuItem>
                  ))}
              </Select>
            </FormControl>
            </Grid>
      </Grid>
      <Grid container direction="row" spacing={2}>
        <Grid item xs={12} md={6} xl={6}>
          <div>
          <ResponsiveContainer width="100%" height={450}>
            <ComposedChart
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
              <Legend />
              <Bar dataKey="pv" name="Paid invoices" stackId="a" fill="#8884d8" />
              <Bar dataKey="de" name="Delayed" stackId="a" fill="#FF4C4C"></Bar>
              <Bar dataKey="uv" name="To pay" stackId="a" fill="#82ca9d">
                <LabelList content={data.pv + data.uv} position="top"/>
              </Bar>
              <Line dataKey="amt" name="Budget" type="monotone" stroke="#ff7300"/>
              </ComposedChart>
          </ResponsiveContainer>
          </div>
        </Grid>
        <Grid item xs={12} md={6} xl={6}>
          <div >
          <ResponsiveContainer width="100%" height={450}>
            <LineChart
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
              <Legend />
                <Line dataKey="pv" name="Liabilities" type="monotone" stroke="#ff7300" />
                <Line dataKey="amt" name="Receivables" type="monotone" stroke="#FF4C4C"/>
              </LineChart>
              </ResponsiveContainer>
          </div>
        </Grid>
        <Grid item xs={12} md={12} xl={12}>
          <div style={{backgroundColor:"grey", height:"500px"}}>
            <p>Widok tygodnia z harmonogramem dostaw/zakupów</p>
          </div>
        </Grid>
		</Grid>
    </div>
);
}

export default PurchaseDashboard;


/*
 <Grid item xs={12} md={4} xl={4}>
        <div style={{height:"450px"}}>
        <ResponsiveContainer width="100%" height="100%">
          <PieChart	minwidth={400} minheight={400}>
            <Pie activeIndex={activeindex}
            activeShape={renderActiveShape}
            data={data02}
            cx="50%"
            cy="50%"
            innerRadius={100}
            outerRadius={140}
            fill="#8884d8"
            dataKey="value"
            onMouseEnter={onPieEnter}/>
          </PieChart>
        </ResponsiveContainer>   
        </div>
			</Grid>
      */