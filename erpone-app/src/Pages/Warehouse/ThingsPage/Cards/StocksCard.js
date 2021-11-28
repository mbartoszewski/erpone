import React, { useState } from 'react'
import { apiStates, useApi } from '../../../../Components/Fetch'
import TableComponentForDetailView from '../../../../Components/ThingsCardDocumentsTable'
import { useParams } from "react-router-dom";

const errorMsg =
{
  position: 'absolute',
  top: '50%',
  left: '50%'
}
function StocksCard()
{
  const { id } = useParams();
  const { state, error, data } = useApi(`http://localhost:5000/api/documents/details/last?thing=${id}&size=7&type=zm`);
  const fetchedData = React.useMemo(() => data, state);
  const columns = React.useMemo(() => [
      { Header: 'Document', accessor: 'document.docNumber' },
      { Header: 'Quantity', accessor: 'quantity' },
      { Header: 'Units', accessor: 'thing.unit.code' },
      { Header: 'Status', accessor: 'document.statusTypeEnum' }], []);
  switch (state)
  {
    case apiStates.ERROR:
    case apiStates.EMPTY:
      return <p sx={errorMsg}>Error: {error} || 'General error'</p>;
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
      return <p sx={errorMsg}>Loading....</p>;
  }
};
export default StocksCard;