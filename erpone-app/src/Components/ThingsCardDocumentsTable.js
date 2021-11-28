import React from 'react'
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import { __RouterContext } from 'react-router';
import { Link } from "react-router-dom";
import {
  useGlobalFilter,
  useRowSelect,
  useSortBy,
  useTable,
} from 'react-table'

const ThingsCardDocumentsTable = ({ title, columns, data }) =>
{
	const {
		rows,
		prepareRow,
		getTableBodyProps,
	} = useTable({ data, columns })
	return (
		<div>
			<TableContainer>
				<Table size = "small">
					<TableHead>
						<TableRow>
							{columns.map((columns) => (
								<TableCell>
									{columns.Header}
								</TableCell>
							))}
							{console.log(data)}
						</TableRow>
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
												return(
												<TableCell {...cell.getCellProps()} component={Link} to={`/warehouse/documents/${row.original.document.id}/details`}>
													{cell.render('Cell')}
												</TableCell>)
											}
											)
										}
								</TableRow>
								)
							})
						}
					</TableBody>
				</Table>
			</TableContainer>
		</div>
	);
}
export default ThingsCardDocumentsTable