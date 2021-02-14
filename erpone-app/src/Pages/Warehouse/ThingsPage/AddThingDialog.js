import React, { useState, useContext } from 'react'
import { makeStyles } from '@material-ui/core/styles';
import AddIcon from '@material-ui/icons/Add'
import Button from '@material-ui/core/Button'
import Dialog from '@material-ui/core/Dialog'
import DialogContent from '@material-ui/core/DialogContent'
import IconButton from '@material-ui/core/IconButton'
import PropTypes from 'prop-types'
import Switch from '@material-ui/core/Switch'
import TextField from '@material-ui/core/TextField'
import Tooltip from '@material-ui/core/Tooltip'
import { InputLabel, Select, MenuItem } from '@material-ui/core'
import { globalStateContext } from '../../ErpOneApp'
import Slide from '@material-ui/core/Slide';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import CloseIcon from '@material-ui/icons/Close';
import { apiStates, useApi} from '../../../Components/Fetch';

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
  description: '',
  unit: {id: ''},
  warehouse: {id: ''}, 
}

const Transition = React.forwardRef(function Transition(props, ref)
{
  return <Slide direction="up" ref={ref} {...props} />;
});

const AddThingDialog = props =>
{
  const classes = useStyles();
  const [thing, setThing] = useState(initialThing)
  const { addThingHandler } = props
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
  
  const handleAdd = event => {
    setThing(initialThing)
    console.log(thing);
    switchState.addMultiple ? setOpen(true) : setOpen(false)
  }

  const handleChange = name => ({ target: { value } }) => {
    setThing({ ...thing, [name]: value })
  }
  return (
    <div>
      <IconButton aria-label="Add thing" onClick={handleClickOpen}>
        <AddIcon/>
      </IconButton>
      <Dialog fullScreen open={open} onClose={handleClose} TransitionComponent={Transition}>
        <AppBar className={classes.appBar}>
          <Toolbar>
            <IconButton edge="start" color="inherit" onClick={handleClose} aria-label="close">
              <CloseIcon />
            </IconButton>
            <Typography variant="h6" className={classes.title}>
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
        <DialogContent>
          <TextField
            autoFocus
            margin="dense"
            label="Code"
            type="text"
            fullWidth
            value={thing.code}
            onChange={handleChange('code')}
          />
          <TextField
            margin="dense"
            label="Description"
            multiline
            type="text"
            fullWidth
            value={thing.description}
            onChange={handleChange('description')}
          />
          <InputLabel id="unit">Unit</InputLabel>
          <Select fullWidth margin="dense" select='true' labelId="unit" id="selectUnit" onChange={handleChange('unit')}>
            {globalContext.dataUnits.map((unit) => { return <MenuItem key={unit.id} value = {unit.id}>{unit.name}</MenuItem>})}
          </Select>
           <InputLabel id="warehouse">Warehouse</InputLabel>
            <Select fullWidth margin="dense" select='true' labelId ="warehouse" id="selectWarehouse" onChange={handleChange('warehouse')}>
            {globalContext.dataWarehouses.map((warehouses) => { return <MenuItem key={warehouses.id} value = {warehouses.id}>{warehouses.name}</MenuItem>})}
            </Select>
        </DialogContent>
      </Dialog>
    </div>
  )
}

AddThingDialog.propTypes = {
  addThingHandler: PropTypes.func.isRequired,
}

export default AddThingDialog;
