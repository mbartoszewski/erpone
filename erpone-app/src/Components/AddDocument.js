import React, { useState, useContext } from 'react'
import { makeStyles } from '@mui/styles';
import Button from '@mui/material/Button'
import {Box, Grid, IconButton, Typography } from '@mui/material';
import PropTypes from 'prop-types'
import Switch from '@mui/material/Switch'
import TextField from '@mui/material/TextField'
import { InputLabel, Select, MenuItem } from '@mui/material'
import { globalStateContext } from '../Pages/ErpOneApp'
import Slide from '@mui/material/Slide';
import AppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import CloseIcon from '@material-ui/icons/Close';
import { apiStates, useApi} from './Fetch';
import { List, Divider, Tooltip, Zoom } from '@mui/material';

const useStyles = makeStyles((theme) => ({
  appBar: {
    position: 'relative',
  },
  title: {
    marginLeft: theme.spacing(2),
    flex: 1,
  },
}));

const initialThing = {
  code: '',
  name: '',
  unit: {id: null},
  warehouse: {id: null}
}

const Transition = React.forwardRef(function Transition(props, ref)
{
  return <Slide direction="up" ref={ref} {...props} />;
});

const AddDocument = props =>
{
  const classes = useStyles();
  const [thing, setThing] = useState(initialThing)
  const { addDocumentHandler } = props
  const [open, setOpen] = React.useState(false)
  const [switchState, setSwitchState] = React.useState({addMultiple: false,})
  const globalContext = useContext(globalStateContext);

  const handleSwitchChange = thingInfo => event => {
    setSwitchState({ ...switchState, [thingInfo]: event.target.checked })
  }

  const resetSwitch = () => {
    setSwitchState({ addMultiple: false })
  }

  const handleClickOpen = () => {
    setOpen(true)
  }

  const handleClose = () => {
    setOpen(false)
    resetSwitch()
  }
  const handleAdd = async e =>
  {
    e.preventDefault();
    //setThing(initialThing)
    console.log(thing);
    //useApi('http://localhost:5000/api/things/');
    await fetch("http://localhost:5000/api/things/", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(thing)
    });
    switchState.addMultiple ? setOpen(true) : setOpen(false)  
  }

  
  const handleChange = name => ({ target: { value } }) =>
  {
    if (name === "warehouse.id")
    {
      setThing({ ...thing, warehouse: {id: value}})
    }
     else if (name === "unit.id")
    {
      setThing({ ...thing, unit: {id: value}})
    }
    else
    {
      setThing({ ...thing, [name]: value })      
    }
  }
  return (
    <div>
        <AppBar className={classes.appBar}>
          <Toolbar>
            <IconButton edge="start" color="inherit" onClick={handleClose} aria-label="close">
              <CloseIcon />
            </IconButton>
            <Typography variant="h5" className={classes.title}>
              Add thing
            </Typography>
             <Tooltip title="Add multiple">
            <Switch
              checked={switchState.addMultiple}
              onChange={handleSwitchChange('addMultiple')}
              value="addMultiple"
              inputProps={{ 'aria-label': 'secondary checkbox' }}
            />
          </Tooltip>
            <Button autoFocus color="inherit" onClick={handleAdd}>
              Save
            </Button>
          </Toolbar>
        </AppBar>
          <Typography variant="h6">Basic info:</Typography>
          <Divider/>
            <Grid container spacing={2}>
                <Grid item xs={12} xm={10} xl={10}>
                  <TextField
                    autoFocus
                    margin="dense"
                    label="Code"
                    type="text"
                    fullWidth
                    value={thing.code}
                    onChange={handleChange('code')}
                  />
                </Grid>
                <Grid item xs={12} xm={10} xl={10}>
                  <TextField
                    margin="dense"
                    label="Description"
                    multiline
                    type="text"
                    fullWidth
                    value={thing.name}
                    onChange={handleChange('name')}
                  />
                </Grid>
                <Grid item xs={8} xm={5} xl={5}>
                  <InputLabel id="unit">Unit</InputLabel>
                  <Select fullWidth margin="dense" select='true' labelId="unit" id="selectUnit" onChange={handleChange('unit.id')}>
                    {globalContext.dataUnits.map((unit) => { return <MenuItem key={unit.id} value = {unit.id}>{unit.name}</MenuItem>})}
                  </Select>
                </Grid>
                <Grid item xs={8} xm={5} xl={5}>
                  <InputLabel id="warehouse">Warehouse</InputLabel>
                  <Select fullWidth margin="dense" select='true' labelId ="warehouse" id="selectWarehouse" onChange={handleChange("warehouse.id")}>
                    {globalContext.dataWarehouses.map((warehouses) => { return <MenuItem key={warehouses.id} value = {warehouses.id}>{warehouses.name}</MenuItem>})}
                  </Select>
                </Grid>
              </Grid>
          <Typography variant="h6">Details:</Typography>
          <Divider />
          <Grid container spacing={2}>
            <Grid item xs={12} xm={4} xl={3}>
              <TextField
                  margin="dense"
                  label="Width"
                  type="number"
                  fullWidth
                // onChange={handleChange('description')}
              />
            </Grid>
              <Grid item xs={12} xm={4} xl={3}>
              <TextField
                  margin="dense"
                  label="Length [mm]"
                  type="number"
                  fullWidth
                  //onChange={handleChange('description')}
                />
                </Grid>
              <Grid item xs={12} xm={4} xl={3}>
              <TextField
                  margin="dense"
                  label="Height [mm]"
                  type="number"
                  fullWidth
                  //onChange={handleChange('description')}
                />
                </Grid>
              <Grid item xs={12} xm={4} xl={3}>
              <TextField
                  margin="dense"
                  label="Weight [kg]"
                  type="number"
                  fullWidth
                  //onChange={handleChange('description')}
                />
                </Grid>
          </Grid>   
    </div>
  )
}

AddDocument.propTypes = {
  addDocumentHandler: PropTypes.func.isRequired,
}

export default AddDocument;
