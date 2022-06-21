import React, { useState } from 'react'
import { apiStates, useFetch } from '../../../../Components/Fetch'
import ThingsCardDocumentsTable from '../../../../Components/ThingsCardDocumentsTable'
import { useParams } from "react-router-dom";

const errorMsg =
{
  position: 'absolute',
  top: '50%',
  left: '50%'
}
function LastDocumentsCard()
{
  const { id } = useParams();
  const [{data: lastDocuments}, doLastDocuments ] = useFetch(`http://localhost:5000/api/documents/details/last?thing=${id}&size=7&type=wz&type=rw&type=pw&type=pz`,null);
  const fetchedData = React.useMemo(() => lastDocuments.data, [lastDocuments.state]);
  const columns = React.useMemo(() => [
    { Header: 'Document', accessor: 'document.docNumber' },
        { Header: 'Contractor', accessor: 'document.contractor.name' },
      { Header: 'Quantity', accessor: 'quantity' },
      { Header: 'Units', accessor: 'thing.unit.code' },
      { Header: 'Status', accessor: 'document.documentStatusEnum' }], []);
  switch (lastDocuments.state)
  {
  case apiStates.ERROR:
	case apiStates.EMPTY:
      return <p sx={errorMsg}>Error: {lastDocuments.error} || 'General error'</p>;
    case apiStates.SUCCESS:
      return (
          <ThingsCardDocumentsTable
            data={fetchedData}
            columns={columns}
            manual
          />
      );
    default:
      return <p sx={errorMsg}>Loading....</p>;
  }
};
export default LastDocumentsCard;