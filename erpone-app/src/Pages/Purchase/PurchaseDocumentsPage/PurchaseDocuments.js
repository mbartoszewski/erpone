import React from 'react'
import { apiStates, useFetch } from '../../../Components/Fetch'
import DocumentsTable from '../../../Components/DocumentsTable'
import DropDownMenu from '../../../Components/DropDownMenu'
import AddIcon from '@mui/icons-material/Add';
import { docStates } from '../../../Components/Helpers';

  const errorMsg = (theme) => ({
  position: 'absolute',
  top: '50%',
  left: '50%'
})

const options = [{title: 'ZM', function: undefined, pathname: "/documents/add", state: {docState: docStates.ADD}},];

const PurchaseDocuments = () =>
{
  const [{data: purchaseDocuments}, doPurchaseDocuments] = useFetch('http://localhost:5000/api/documents?type=zm&type=pz&type=pw', null);
  const fetchedData = React.useMemo(() => purchaseDocuments.data, [purchaseDocuments.state]);
  const columns = React.useMemo(() => [
    { Header: 'Status', accessor: 'documentStatusEnum' },
    { Header: 'Document', accessor: 'docNumber' },
    { Header: 'Type', accessor: 'documentTypeEnum' },
    { Header: "Contractor", accessor: "contractor.name" },
    { Header: 'Created', accessor: 'createdAt' },
    { Header: 'Target', accessor: 'targetDateTime' },
    { Header: 'Description', accessor: 'description' }], []);

  switch (purchaseDocuments.state)
  {
    case apiStates.ERROR:
    case apiStates.EMPTY:
      return <p sx={errorMsg}>Error: {purchaseDocuments.error} || 'General error'</p>;
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
           detailLink={"purchase/documents/details"}
        />
      );
    default:
      return <p sx={errorMsg}>Loading....</p>;
  }
}
export default PurchaseDocuments;
