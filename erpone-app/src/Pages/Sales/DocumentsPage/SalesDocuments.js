import React from 'react'
import { apiStates, useFetch } from '../../../Components/Fetch'
import DropDownMenu from '../../../Components/DropDownMenu'
import AddIcon from '@mui/icons-material/Add';
import DocumentsTable from '../../../Components/DocumentsTable'

const errorMsg = (theme) => ({
  position: 'absolute',
  top: '50%',
  left: '50%'
})
const options = ['PZ', 'WZ'];

const SalesDocuments = () =>
{
  const [{data: salesDocuments}, doSalesDocuments] = useFetch('http://localhost:5000/api/documents?type=fv&type=wz', null);
  const fetchedData = React.useMemo(() => salesDocuments.data, [salesDocuments.state]);
  const columns = React.useMemo(() => [
    { Header: 'Status', accessor: 'documentStatusEnum' },
    { Header: 'Document', accessor: 'docNumber' },
    { Header: 'Type', accessor: 'documentTypeEnum' },
    { Header: "Contractor", accessor: "contractor.name" },
    { Header: 'Created', accessor: 'createdAt' },
    { Header: 'Target', accessor: 'targetDateTime' },
    { Header: 'Description', accessor: 'description' }
    ], []);
  switch (salesDocuments.state)
  {
    case apiStates.ERROR:
    case apiStates.EMPTY:
      return <p sx={errorMsg}>Error: {salesDocuments.error} || 'General error'</p>;
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
           detailLink={"sales/documents/details"}
                />
      );
    default:
      return <p sx={errorMsg}>Loading....</p>;
  }
}
export default SalesDocuments;
