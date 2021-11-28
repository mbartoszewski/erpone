import React from 'react'
import { Link } from "react-router-dom";
import MaUTable from '@mui/material/Table'
import TableBody from '@mui/material/TableBody'
import TableCell from '@mui/material/TableCell'
import TableContainer from '@mui/material/TableContainer'
import TableHead from '@mui/material/TableHead'
import TableRow from '@mui/material/TableRow'
import { useParams } from "react-router-dom";
import DeleteForeverIcon from '@mui/icons-material/DeleteForever';
import {
  useGlobalFilter,
  useSortBy,
  useTable,
} from 'react-table'
import { TableFooter } from '@mui/material';


const DocumentsDetailsTable = ({data, columns, isEdit}) =>
{
	const { id } = useParams();
	const {
		getTableProps,
		getTableBodyProps,
		headerGroups,
		footerGroups,
		rows,
		prepareRow,
	} = useTable({ data, columns, isEdit}, useSortBy)
		
	return (
		<TableContainer >
				<MaUTable {...getTableProps()}size = "small" >
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
				<TableFooter>
					{footerGroups.map(group => (
						<TableRow {...group.getFooterGroupProps()}>
							{group.headers.map(column => (
								<TableCell {...column.getFooterProps()}>
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