import React, { useState, useEffect} from 'react'
import CssBaseline from '@material-ui/core/CssBaseline'
import ThingsTable from '../../Components/Warehouse/ThingsTable/ThingsTable'
import fetch from '../../Components/Fetch';
const Warehouse = () =>
{
  const rest = fetch('http://localhost:5000/api/things/');
  const data = React.useMemo(() => rest, rest);
  const columns = React.useMemo(
    () => [
	{ Header: 'Code', accessor: 'code'},
	{ Header: 'Name', accessor: 'name'},
	{ Header: 'Quantity', accessor: 'quantity'},
],
    []
  )

  return (
    <div>
      <CssBaseline />
      <ThingsTable
        data={data}
        columns={columns}
        manual
      />
    </div >
  )
}
export default Warehouse
