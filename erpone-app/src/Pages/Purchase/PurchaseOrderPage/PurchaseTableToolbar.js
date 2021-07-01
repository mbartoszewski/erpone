import React from 'react'

import AddDocument from '../../../Components/AddDocument'
import clsx from 'clsx'
import GlobalFilter from '../../Warehouse/ThingsPage/GlobalFilter'
import { lighten, makeStyles } from '@material-ui/core/styles'
import PropTypes from 'prop-types'
import Toolbar from '@material-ui/core/Toolbar'
import {Box, Grid, IconButton, Typography } from '@material-ui/core';
import AddIcon from '@material-ui/icons/Add'
import { Link } from "react-router-dom";


const useToolbarStyles = makeStyles(theme => ({
  root: {
    paddingLeft: theme.spacing(2),
    paddingRight: theme.spacing(1),
    backgroundColor: '#ddf6dd',
    display: "flex",
    justifyContent: "flex-start"
  },

  addThingButton: {
    flex: '1 1 100%',
  },
   title: {
    flex: '0 0 96%',
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

const PurchaseTableToolbar = props => {
  const classes = useToolbarStyles()
  const {
    numSelected,
    addDocumentHandler,
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
      <div className = {classes.addThingButton}>
        <IconButton aria-label="Add document" component={Link} to={`/documents/add`}>
          <AddIcon/>
        </IconButton>
      </div>
      {numSelected > 0 ? (<Typography className = {classes.title}>{numSelected} selected</Typography>) : 
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

PurchaseTableToolbar.propTypes = {
  numSelected: PropTypes.number.isRequired,
  addDocumentHandler: PropTypes.func.isRequired,
  setGlobalFilter: PropTypes.func.isRequired,
  preGlobalFilteredRows: PropTypes.array.isRequired,
  globalFilter: PropTypes.string.isRequired,
}

export default PurchaseTableToolbar
