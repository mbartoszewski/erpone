import React from 'react'
import { makeStyles } from '@material-ui/core/styles'
import PurchaseOrderTable from './PurchaseOrderTable'
import { apiStates, useApi } from '../../../Components/Fetch'

const useStyles = makeStyles(theme => ({
  errorMsg: {
  position: 'absolute',
  top: '50%',
  left: '50%'
}
}))

const PurchaseOrders = () =>
{
  const classes = useStyles()
  const { state, error, data } = useApi('http://localhost:5000/api/documents?type=zm');
  const fetchedData = React.useMemo(() => data, data);
  const columns = React.useMemo(() => [
    { Header: "Status", accessor: "statusTypeEnum" },
      { Header: 'Number', accessor: 'docNumber' },
      { Header: 'Created At', accessor: 'createdAt' },
      { Header: 'Target Date', accessor: 'targetDateTime' },
    { Header: "Description", accessor: "description" },
  { Header: "Contractor", accessor: "contractor.name" }], []);
  
  switch (state)
  {
    case apiStates.ERROR:
    case apiStates.EMPTY:
      return <p className={classes.errorMsg}>Error: {error} || 'General error'</p>;
    case apiStates.SUCCESS:
      return (
        <div>
          <PurchaseOrderTable
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
export default PurchaseOrders;
