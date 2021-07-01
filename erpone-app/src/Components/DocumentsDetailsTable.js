import React from 'react'
import { Link } from "react-router-dom";
import Checkbox from '@material-ui/core/Checkbox'
import MaUTable from '@material-ui/core/Table'
import TableBody from '@material-ui/core/TableBody'
import TableCell from '@material-ui/core/TableCell'
import TableContainer from '@material-ui/core/TableContainer'
import TableHead from '@material-ui/core/TableHead'
import TableRow from '@material-ui/core/TableRow'
import { useParams } from "react-router-dom";
import {
  useGlobalFilter,
  useSortBy,
  useTable,
} from 'react-table'
import { TableFooter } from '@material-ui/core';
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
const DocumentsDetailsTable = ({data, columns}) =>
{
	const { id } = useParams();
	const {
		getTableProps,
		getTableBodyProps,
		headerGroups,
		footerGroups,
		rows,
		prepareRow,
	} = useTable({ data, columns }, useSortBy)
		
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
												return cell.getCellProps().key.includes("selection") ?  (
													<TableCell {...cell.getCellProps()} >
														{cell.render('Cell')}
													</TableCell>) :
															 (
														<TableCell {...cell.getCellProps()} component={Link} to={`/warehouse/things/${row.original.thing.id}`}>
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