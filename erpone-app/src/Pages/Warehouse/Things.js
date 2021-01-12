import React, { useState, useEffect} from 'react'
import CssBaseline from '@material-ui/core/CssBaseline'
import { makeStyles } from '@material-ui/core/styles'
import ThingsTable from '../../Components/Warehouse/ThingsTable/ThingsTable'
import { apiStates, useApi } from '../../Components/Fetch'

const useStyles = makeStyles(theme => ({
  errorMsg: {
  position: 'absolute',
  top: '50%',
  left: '50%'
}
}))

const Warehouse = () =>
{
  const classes = useStyles()
  const { state, error, data } = useApi('http://localhost:5000/api/things/');
  const fetchedData = React.useMemo(() => data, data);
  const columns = React.useMemo(() => [
      { Header: 'Code', accessor: 'code' },
      { Header: 'Name', accessor: 'name' },
      { Header: 'Quantity', accessor: 'quantity' },
      { Header: "unit", accessor: "unit.code" }], []);
  
  switch (state)
  {
    case apiStates.ERROR:
      return <p className={classes.errorMsg}>Error: {error} || 'General error'</p>;
    case apiStates.SUCCESS:
      return (
        <div>
          <CssBaseline />
          <ThingsTable
            data={fetchedData}
            columns={columns}
            manual
          />
        </div >
      );
    default:
      return <p className={classes.errorMsg}>Loading....</p>;
  }
}
export default Warehouse
