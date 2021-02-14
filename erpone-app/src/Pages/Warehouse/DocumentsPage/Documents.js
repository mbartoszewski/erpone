import React from 'react'
import { makeStyles } from '@material-ui/core/styles'
import WarehouseDocumentsTable from './DocumentsTable'
import { apiStates, useApi } from '../../../Components/Fetch'

const useStyles = makeStyles(theme => ({
  errorMsg: {
  position: 'absolute',
  top: '50%',
  left: '50%'
}
}))

const WarehouseDocuments = () =>
{
  const classes = useStyles()
  const { state, error, data } = useApi('http://localhost:5000/api/documents/all');
  const fetchedData = React.useMemo(() => data, data);
  const columns = React.useMemo(() => [
    { Header: 'Status', accessor: 'statusTypeEnum' },
    { Header: 'Document', accessor: 'docNumber' },
    { Header: 'Type', accessor: 'documentTypeEnum' },
    { Header: "Contractor", accessor: "contractor.name" },
    { Header: 'Created', accessor: 'createdAt' },
    { Header: 'Target', accessor: 'targetDateTime' },
    { Header: 'Description', accessor: 'description' }
    ], []);
  switch (state)
  {
    case apiStates.ERROR:
      return <p className={classes.errorMsg}>Error: {error} || 'General error'</p>;
    case apiStates.SUCCESS:
      return (
        <div>
          <WarehouseDocumentsTable
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
export default WarehouseDocuments;
