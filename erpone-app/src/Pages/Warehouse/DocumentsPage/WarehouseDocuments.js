import React from 'react'
import { apiStates, useFetch } from '../../../Components/Fetch'
import DropDownMenu from '../../../Components/DropDownMenu'
import AddIcon from '@mui/icons-material/Add';
import DocumentsTable from '../../../Components/DocumentsTable'

import { docStates } from '../../../Components/Helpers';

const errorMsg = (theme) => ({
  position: 'absolute',
  top: '50%',
  left: '50%'
})

const addButtonCliclHandler = (documentType) => {  }
const options = [{title: 'PZ', function: addButtonCliclHandler, pathname: "/documents/add", state: {docState: docStates.ADD}}, {title: 'WZ', function: addButtonCliclHandler, pathname: "/documents/add", state: {docState: docStates.ADD}}, {title: 'PW', function: undefined, pathname: "/documents/add", state: {docState: docStates.ADD}}];

const WarehouseDocuments = () =>
{

  const [{data: warehouseDocuments}, doWarehouseDocuments] = useFetch('http://localhost:5000/api/documents?type=pw&type=pz&type=rw&type=wz', null);
  const fetchedData = React.useMemo(() => warehouseDocuments.data, [warehouseDocuments.state]);
  const columns = React.useMemo(() => [
    { Header: 'Status', accessor: 'documentStatusEnum' },
    { Header: 'Document', accessor: 'docNumber' },
    { Header: 'Type', accessor: 'documentTypeEnum' },
    { Header: "Contractor", accessor: "contractor.name" },
    { Header: 'Created', accessor: 'createdAt' },
    { Header: 'Target', accessor: 'targetDateTime' },
    { Header: 'Description', accessor: 'description' }
    ], []);
  switch (warehouseDocuments.state)
  {
    case apiStates.ERROR:
    case apiStates.EMPTY:
      return <p sx={errorMsg}>Error: {warehouseDocuments.error} || 'General error'</p>;
    case apiStates.SUCCESS:
      return (
            <DocumentsTable
            data={fetchedData}
            columns={columns}
            manual
            tToolbar={<DropDownMenu
            buttonTitle={"Add"}
            menuOptions={options}
            icon={<AddIcon/>}>
          </DropDownMenu>}
           detailLink={"warehouse/documents/details"}
                />
      );
    default:
      return <p sx={errorMsg}>Loading....</p>;
  }
}
export default WarehouseDocuments;
