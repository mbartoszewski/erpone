import React, { useState } from 'react'

import AddIcon from '@material-ui/icons/Add'
import Button from '@material-ui/core/Button'
import Dialog from '@material-ui/core/Dialog'
import DialogActions from '@material-ui/core/DialogActions'
import DialogContent from '@material-ui/core/DialogContent'
import DialogTitle from '@material-ui/core/DialogTitle'
import IconButton from '@material-ui/core/IconButton'
import PropTypes from 'prop-types'
import Switch from '@material-ui/core/Switch'
import TextField from '@material-ui/core/TextField'
import Tooltip from '@material-ui/core/Tooltip'
import { MenuItem } from '@material-ui/core'

const initialThing = {
  code: '',
  eanCode: '',
  description: '',
  stock: 0,
  unit: '',
  warehouse: '',
  subRows: undefined,
}

const units = [
  {
    value: 'szt',
    label: 'sztuki'
  },
   {
    value: 'kg',
    label: 'kilogramy'
  },
  {
    value: 'krt',
    label: 'karton'
  },
]
const AddThingDialog = props => {
  const [thing, setThing] = useState(initialThing)
  const { addThingHandler } = props
  const [open, setOpen] = React.useState(false)
  const [switchState, setSwitchState] = React.useState({
    addMultiple: false,
  })

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
    addThingHandler(thing)
    setThing(initialThing)
    switchState.addMultiple ? setOpen(true) : setOpen(false)
  }

  const handleChange = name => ({ target: { value } }) => {
    setThing({ ...thing, [name]: value })
  }

  return (
    <div>
      <Tooltip title="Add">
        <IconButton aria-label="add" onClick={handleClickOpen}>
          <AddIcon />
        </IconButton>
      </Tooltip>
      <Dialog
        open={open}
        onClose={handleClose}
        aria-labelledby="form-dialog-title"
      >
        <DialogTitle id="form-dialog-title">Add Thing</DialogTitle>
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
            autoFocus
            margin="dense"
            label="EAN code"
            type="text"
            fullWidth
            value={thing.eanCode}
            onChange={handleChange('eanCode')}
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
          <TextField
            margin="dense"
            select
            label="Units"
            fullWidth
            value={thing.units}
            onChange={handleChange('unit')}
          >
            {units.map((units) => (
              <MenuItem value={units.value} label={units.value}> {units.label}</MenuItem>
            ))}
          </TextField>
           <TextField
            margin="dense"
            select
            label="Warehouse"
            fullWidth
            value={thing.warehouse}
            onChange={handleChange('warehouse')}
          >
            {units.map((units) => (
              <MenuItem value={units.value} label={units.value}> {units.label}</MenuItem>
            ))}
          </TextField>
        </DialogContent>
        <DialogActions>
          <Tooltip title="Add multiple">
            <Switch
              checked={switchState.addMultiple}
              onChange={handleSwitchChange('addMultiple')}
              value="addMultiple"
              inputProps={{ 'aria-label': 'secondary checkbox' }}
            />
          </Tooltip>
          <Button onClick={handleClose} color="primary">
            Cancel
          </Button>
          <Button onClick={handleAdd} color="primary">
            Add
          </Button>
        </DialogActions>
      </Dialog>
    </div>
  )
}

AddThingDialog.propTypes = {
  addThingHandler: PropTypes.func.isRequired,
}

export default AddThingDialog
