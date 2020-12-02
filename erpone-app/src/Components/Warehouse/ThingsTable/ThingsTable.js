import React from 'react'
import { Link } from "react-router-dom";
import Checkbox from '@material-ui/core/Checkbox'
import MaUTable from '@material-ui/core/Table'
import PropTypes from 'prop-types'
import TableBody from '@material-ui/core/TableBody'
import TableCell from '@material-ui/core/TableCell'
import TableContainer from '@material-ui/core/TableContainer'
import TableHead from '@material-ui/core/TableHead'
import TableRow from '@material-ui/core/TableRow'
import TableSortLabel from '@material-ui/core/TableSortLabel'
import TableToolbar from './TableToolbar'
import {
  useGlobalFilter,
  useRowSelect,
  useSortBy,
  useTable,
} from 'react-table'

const ThingsTable = ({data, columns}) =>
	{
	const {
		getTableProps,
		getTableBodyProps,
		headerGroups,
		rows,
		prepareRow,
		} = useTable({ data, columns })
		
		return	(
			<MaUTable {...getTableProps()}>
				<TableHead>
					{headerGroups.map(headerGroup => (
						<TableRow {...headerGroup.getHeaderGroupProps()}>
							{headerGroup.headers.map(column => (
								<TableCell {...column.getHeaderProps()}>
									{column.render('Header')}
								</TableCell>))}
						</TableRow>))}
				</TableHead>
				<TableBody {...getTableBodyProps()}>
					{
						rows.map(row =>
						{
							prepareRow(row)
							return (
								<TableRow {...row.getRowProps()}>
									{
										row.cells.map(cell => 
										{
											return (
												<TableCell {...cell.getCellProps()}>
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
		);
};
export default ThingsTable;