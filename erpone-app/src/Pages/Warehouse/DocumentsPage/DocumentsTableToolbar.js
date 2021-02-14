import React from 'react'

import AddThingDialog from '../ThingsPage/AddThingDialog'
import clsx from 'clsx'
import GlobalFilter from '../ThingsPage/GlobalFilter'
import { lighten, makeStyles } from '@material-ui/core/styles'
import PropTypes from 'prop-types'
import Toolbar from '@material-ui/core/Toolbar'
import Typography from '@material-ui/core/Typography'


const useToolbarStyles = makeStyles(theme => ({
  root: {
    paddingLeft: theme.spacing(2),
    paddingRight: theme.spacing(1),
              backgroundColor: '#ddf6dd',

  },

  addThingButton: {
    flex: 'auto',
  },
   title: {
    flex: '1 1 100%',
  },
    selected: {
      flex: '1 1 100%',
      margin: theme.spacing(1),
  },
    searchBox: {
      flex: 'initial',
  },
  highlight:
    theme.palette.type === 'light'
      ? {
          color: theme.palette.secondary.main,
          backgroundColor: lighten(theme.palette.secondary.light, 0.85),
        }
      : {
          color: theme.palette.text.primary,
          backgroundColor: theme.palette.secondary.dark,
        },
}))

const TableToolbar = props => {
  const classes = useToolbarStyles()
  const {
    title,
    numSelected,
    addThingHandler,
    preGlobalFilteredRows,
    setGlobalFilter,
    globalFilter,
  } = props
  return (
    <Toolbar
      className={clsx(classes.root, {
        [classes.highlight]: numSelected > 0,
      })}
    >
      <Typography variant="h6">
        {title}
      </Typography>
      <div className = {classes.addThingButton}>
        <AddThingDialog addThingHandler={addThingHandler} />
      </div>
      {numSelected > 0 ? (<Typography className = {classes.selected}>{numSelected} selected</Typography>) : 
      (
        <div className={classes.searchBox}>
          <GlobalFilter 
          preGlobalFilteredRows={preGlobalFilteredRows}
          globalFilter={globalFilter}
          setGlobalFilter={setGlobalFilter}/>
      </div>)}
  
    </Toolbar>    
  )
}

TableToolbar.propTypes = {
  numSelected: PropTypes.number.isRequired,
  addThingHandler: PropTypes.func.isRequired,
  setGlobalFilter: PropTypes.func.isRequired,
  preGlobalFilteredRows: PropTypes.array.isRequired,
  globalFilter: PropTypes.string.isRequired,
  title: PropTypes.string.isRequired,
}

export default TableToolbar
