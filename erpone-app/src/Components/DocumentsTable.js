import React from 'react'
import { useParams } from "react-router-dom";
import { Link } from "react-router-dom";
import Checkbox from '@mui/material/Checkbox'
import MaUTable from '@mui/material/Table'
import TableBody from '@mui/material/TableBody'
import TableCell from '@mui/material/TableCell'
import TableContainer from '@mui/material/TableContainer'
import TableHead from '@mui/material/TableHead'
import TableRow from '@mui/material/TableRow'
import TableToolbar from './TableToolbar'
import ReactDOM from 'react-dom'
import {
  useGlobalFilter,
  useRowSelect,
  useSortBy,
  useTable,
} from 'react-table'
import { TableFooter } from '@mui/material';

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

const DocumentsTable = ({data, columns, tToolbar, detailLink}) =>
{
	const { id } = useParams();
	const {
	
		getTableProps,
		getTableBodyProps,
		headerGroups,
		footerGroups,
		rows,
		prepareRow,
		preGlobalFilteredRows,
		setGlobalFilter,
		state:{globalFilter, selectedRowIds},
	} = useTable({ data, columns, tToolbar, detailLink}, useGlobalFilter, useSortBy, useRowSelect, 
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
		<TableContainer sx={{maxHeight: "800px", }}>
			{ReactDOM.createPortal(<TableToolbar
				dropDownMenu={tToolbar}
				numSelected={Object.keys(selectedRowIds).length}
				preGlobalFilteredRows={preGlobalFilteredRows}
				setGlobalFilter={setGlobalFilter}
				globalFilter={globalFilter}/>, document.getElementById("option-toolbar"))}
				<MaUTable {...getTableProps()} size = "small">
				<TableHead sx={{position: "sticky", top: "0px", backgroundColor: "white", zIndex: "1"}}>
						{headerGroups.map(headerGroup => (
							<TableRow {...headerGroup.getHeaderGroupProps()}>
								{headerGroup.headers.map(column => (
									<TableCell {...column.getHeaderProps(column.getSortByToggleProps())} sx={{fontSize: "0.9em", fontWeight: "bold"}}>
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
													<TableCell {...cell.getCellProps()} component={Link} to={`/${detailLink}/${row.original.id}`}>
															{cell.render('Cell')}
													</TableCell>)
											})
										}
								</TableRow>
								)
							})
						}
				</TableBody>
				<TableFooter sx={{backgroundColor: "white", zIndex: "1", position: "sticky", bottom: "0px", borderCollapse: "unset"}}>
					{footerGroups.map(group => (
						<TableRow {...group.getFooterGroupProps()}>
							{group.headers.map(column => (
								<TableCell {...column.getFooterProps()} style={{fontSize: "0.9em", fontWeight: "bold"}}>
									{column.render('Footer')}
								</TableCell>
							))}
						</TableRow>
					))}
				</TableFooter>
				</MaUTable>			
			</TableContainer>
		);
};
export default DocumentsTable;