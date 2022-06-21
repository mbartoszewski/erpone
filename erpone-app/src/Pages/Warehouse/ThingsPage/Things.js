import React from 'react'
import { apiStates, useFetch } from '../../../Components/Fetch'
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
  const [{data: warehouseThings}, doWarehouseThings] = useFetch('http://localhost:5000/api/things/', null);
  const fetchedData = React.useMemo(() => warehouseThings.data, [warehouseThings.state]);
  const columns = React.useMemo(() => [
      { Header: 'Code', accessor: 'code' },
      { Header: 'Name', accessor: 'name' },
      { Header: 'Quantity', accessor: 'quantity' },
      { Header: "Unit", accessor: "unit.code" }], []);
  
  switch (warehouseThings.state)
  {
    case apiStates.ERROR:
    case apiStates.EMPTY:
      return <p sx={errorMsg}>Error: {warehouseThings.error} || 'General error'</p>;
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
