import React from 'react'
import { Link } from "react-router-dom";
import MaUTable from '@mui/material/Table'
import TableBody from '@mui/material/TableBody'
import TableCell from '@mui/material/TableCell'
import TableContainer from '@mui/material/TableContainer'
import TableHead from '@mui/material/TableHead'
import TableRow from '@mui/material/TableRow'
import { useParams } from "react-router-dom";
import {
  useSortBy,
	useTable,
    useRowSelect,

} from 'react-table'
import { TableFooter, Typography } from '@mui/material';

const DocumentsDetailsTable = ({data, columns, docState, handleThingChange, handleDeleteItemClick}) =>
{
	const { id } = useParams();
	
	const {
		getTableProps,
		getTableBodyProps,
		headerGroups,
		footerGroups,
		rows,
		prepareRow,
		state:{selectedRowIds}
	} = useTable({ data, columns, docState, handleThingChange, handleDeleteItemClick}, useSortBy, useRowSelect)
		
	return (
		<TableContainer sx={{ maxHeight: "600px", }}>
				<MaUTable {...getTableProps()} size = "small" >
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
					<TableBody {...getTableBodyProps()} >
						{
							rows.map(row =>
							{
								prepareRow(row)
								return (
									<TableRow {...row.getRowProps()} >
										{
											row.cells.map(cell => 
											{
												return (<TableCell {...cell.getCellProps()} >
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
export default DocumentsDetailsTable;