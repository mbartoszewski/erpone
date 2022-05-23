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

import {
  useGlobalFilter,
  useRowSelect,
  useSortBy,
  useTable,
} from 'react-table'
import { TableFooter } from '@mui/material';

const DeliveriesSheduleTable = ({data, columns, tToolbar, detailLink}) =>
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
		state:{globalFilter},
	} = useTable({ data, columns, tToolbar, detailLink}, useGlobalFilter, useSortBy)
		
	return (
		<TableContainer sx={{maxHeight: "800px", }}>

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
														<TableCell {...cell.getCellProps()} component={Link} to={`/${detailLink}/${row.original[cell.column.id] !== undefined ? row.original[cell.column.id].id : ""}`}>
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
export default DeliveriesSheduleTable;