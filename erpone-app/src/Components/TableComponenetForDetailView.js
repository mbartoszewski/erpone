import React from 'react'
import { makeStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import { __RouterContext } from 'react-router';
import { Link } from "react-router-dom";
import {
  useGlobalFilter,
  useRowSelect,
  useSortBy,
  useTable,
} from 'react-table'
const useStyles = makeStyles((theme) => ({
}))
const TableComponent = ({ title, columns, data }) =>
{
	const classes = useStyles();

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
export default TableComponent