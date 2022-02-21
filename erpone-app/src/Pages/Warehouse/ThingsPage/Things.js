import React from 'react'
import ThingsTable from './ThingsTable'
import { apiStates, useApi } from '../../../Components/Fetch'
import DropDownMenu from '../../../Components/DropDownMenu'
import AddIcon from '@mui/icons-material/Add';
import DocumentsTable from '../../../Components/DocumentsTable'

const errorMsg =
{
  position: 'absolute',
  top: '50%',
  left: '50%'
}

const options = ['Thing'];

const Warehouse = () =>
{
  const { state, error, data } = useApi('http://localhost:5000/api/things/');
  const fetchedData = React.useMemo(() => data, [state]);
  const columns = React.useMemo(() => [
      { Header: 'Code', accessor: 'code' },
      { Header: 'Name', accessor: 'name' },
      { Header: 'Quantity', accessor: 'quantity' },
      { Header: "Unit", accessor: "unit.code" }], []);
  
  switch (state)
  {
    case apiStates.ERROR:
    case apiStates.EMPTY:
      return <p sx={errorMsg}>Error: {error} || 'General error'</p>;
    case apiStates.SUCCESS:
      return (
        <div>
         <DocumentsTable
          data={fetchedData}
          columns={columns}
          manual
           tToolbar={<DropDownMenu
            buttonTitle={"Add"}
            menuOptions={options}
            icon={<AddIcon/>}>
          </DropDownMenu>}
           detailLink={"warehouse/things"}
                />
        </div >
      );
    default:
      return <p sx={errorMsg}>Loading....</p>;
  }
}
export default Warehouse;
