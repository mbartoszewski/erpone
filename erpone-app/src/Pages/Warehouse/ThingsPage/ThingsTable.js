import React from 'react'
import { Link } from "react-router-dom";
import Checkbox from '@mui/material/Checkbox'
import MaUTable from '@mui/material/Table'
import TableBody from '@mui/material/TableBody'
import TableCell from '@mui/material/TableCell'
import TableContainer from '@mui/material/TableContainer'
import TableHead from '@mui/material/TableHead'
import TableRow from '@mui/material/TableRow'
import AddIcon from '@mui/icons-material/Add';
import TableToolbar from '../../../Components/TableToolbar'
import DropDownMenu from '../../../Components/DropDownMenu';
import ReactDOM from 'react-dom';
import {
  useGlobalFilter,
  useRowSelect,
  useSortBy,
  useTable,
} from 'react-table'

const IndeterminateCheckbox = React.forwardRef(({ indeterminate, ...rest }, ref) =>
{
	const defaultRef = React.useRef()
	const resolvedRef = ref || defaultRef

	React.useEffect(() =>
	{
		resolvedRef.current.indeterminate = indeterminate
	}, [resolvedRef, indeterminate])

	return (
		<>
			<Checkbox ref={resolvedRef} {...rest}/>
		</>
	)
})
const options = ['Thing'];
 
const ThingsTable = ({data, columns}) =>
	{
	const {
		getTableProps,
		getTableBodyProps,
		headerGroups,
		rows,
		prepareRow,
		preGlobalFilteredRows,
		setGlobalFilter,
		state:{globalFilter, selectedRowIds},
	} = useTable({ data, columns }, useGlobalFilter, useSortBy, useRowSelect, 
		hooks =>
		{
			hooks.visibleColumns.push(columns => [
				// Let's make a column for selection
				{
					id: 'selection',
					// The header can use the table's getToggleAllRowsSelectedProps method
					// to render a checkbox
					Header: ({ getToggleAllRowsSelectedProps }) => (
						<div>
							<IndeterminateCheckbox {...getToggleAllRowsSelectedProps()} />
						</div>
					),
					// The cell can use the individual row's getToggleRowSelectedProps method
					// to the render a checkbox
					Cell: ({ row }) => (
						<div>
							<IndeterminateCheckbox {...row.getToggleRowSelectedProps()} />
						</div>
					),
				},
				...columns,
			])
		})
		
	return (
		<div>
			{ReactDOM.createPortal(<TableToolbar
				dropDownMenu={
					<DropDownMenu
					buttonTitle={"Add"}
					menuOptions={options}
					icon={<AddIcon/>}>
						</DropDownMenu>}
				numSelected={Object.keys(selectedRowIds).length}
				preGlobalFilteredRows={preGlobalFilteredRows}
				setGlobalFilter={setGlobalFilter}
				globalFilter={globalFilter}/>, document.getElementById("option-toolbar"))}
			<TableContainer>
				<MaUTable {...getTableProps()} size="small">
					<TableHead>
						{headerGroups.map(headerGroup => (
							<TableRow {...headerGroup.getHeaderGroupProps()}>
								{headerGroup.headers.map(column => (
									<TableCell {...column.getHeaderProps(column.getSortByToggleProps())}>
										{column.render('Header')}
										 <span>
                    {column.isSorted
                      ? column.isSortedDesc
                        ? ' 🔽'
                        : ' 🔼'
                      : ''}
                  </span>
									</TableCell>))}
							</TableRow>))}
					</TableHead>
					<TableBody {...getTableBodyProps()}>
						{
							rows.map(row =>
							{
								prepareRow(row)
								return (
									<TableRow {...row.getRowProps()} >
										{
											row.cells.map(cell => 
											{
												return cell.getCellProps().key.includes("selection") ?  (
													<TableCell {...cell.getCellProps()} >
														{cell.render('Cell')}
													</TableCell>) :
															 (
													<TableCell {...cell.getCellProps()} component={Link} to={`/warehouse/things/${row.original.id}`}>
															{cell.render('Cell')}
													</TableCell>)
											})
										}
								</TableRow>
								)
							})
						}
					</TableBody>
				</MaUTable>			
			</TableContainer>
		</div>
		
		);
};
export default ThingsTable;