import React from 'react'

import GlobalFilter from './GlobalFilter'
import PropTypes from 'prop-types'
import Typography from '@mui/material/Typography'
import { Grid } from '@mui/material'

const TableToolbar = (props) => {
  const {
    title,
    numSelected,
    preGlobalFilteredRows,
    setGlobalFilter,
    globalFilter,
    dropDownMenu
  } = props
  return (
     <Grid container spacing={1} justifyContent="space-between" alignItems="center">
          <Grid item xs={4} md={4} xl={4}>
            {dropDownMenu}
          </Grid>
           <Grid item xs={8} md={4} xl={4}>
            <Typography variant="h6">
          {title !== null ? title : ""}
            </Typography>
          </Grid>
          <Grid item xs={12} md={4} xl={4}>
        {numSelected > 0 ?
          (<Typography sx={{ fontSize: '1.2em' }}>{numSelected} selected</Typography>)
          :
          (
            <GlobalFilter 
            preGlobalFilteredRows={preGlobalFilteredRows}
            globalFilter={globalFilter}
            setGlobalFilter={setGlobalFilter}/>
          )}
        </Grid>
        </Grid> 
  )
}

TableToolbar.propTypes = {
  numSelected: PropTypes.number.isRequired,
  setGlobalFilter: PropTypes.func.isRequired,
  preGlobalFilteredRows: PropTypes.array.isRequired,
  globalFilter: PropTypes.string.isRequired,
}

export default TableToolbar
