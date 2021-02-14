import React, { useState } from 'react'
import { makeStyles } from '@material-ui/core/styles'
import { apiStates, useApi } from '../../../../Components/Fetch'
import TableComponentForDetailView from '../../../../Components/TableComponenetForDetailView'
import { useParams } from "react-router-dom";

const useStyles = makeStyles((theme) => ({
	root: {
    display: 'flex',
    flexWrap: 'wrap',
    '& > *': {
      margin: theme.spacing(1),
      width: theme.spacing(16),
      height: theme.spacing(16),
    },
  },

}))
function SalesCard()
{
  const classes = useStyles();
  const { id } = useParams();
  const { state, error, data } = useApi(`http://localhost:5000/api/documents/details/last?thing=${id}&size=7&type=wz&type=wzz&type=rw`);
  const fetchedData = React.useMemo(() => data, data);
  const columns = React.useMemo(() => [
    { Header: 'Document', accessor: 'document.docNumber' },
        { Header: 'Contractor', accessor: 'document.contractor.name' },
      { Header: 'Quantity', accessor: 'quantity' },
      { Header: 'Units', accessor: 'thing.unit.code' },
      { Header: "Target data", accessor: "thing.unit.code" },
      { Header: 'Status', accessor: 'document.statusTypeEnum' }], []);
  switch (state)
  {
    case apiStates.ERROR,
      apiStates.EMPTY:
      return <p className={classes.errorMsg}>Error: {error} || 'General error'</p>;
    case apiStates.SUCCESS:
      return (
        <div>
          <TableComponentForDetailView
            data={fetchedData}
            columns={columns}
            manual
          />
        </div >
      );
    default:
      return <p className={classes.errorMsg}>Loading....</p>;
  }
};
export default SalesCard;