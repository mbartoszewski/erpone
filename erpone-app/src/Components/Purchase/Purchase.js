import React from 'react';
import { useTable, useSortBy, useRowSelect } from 'react-table';
import MaUTable from '@material-ui/core/Table'
import TableBody from '@material-ui/core/TableBody'
import TableCell from '@material-ui/core/TableCell'
import TableHead from '@material-ui/core/TableHead'
import TableRow from '@material-ui/core/TableRow'
import ExpandLess from '@material-ui/icons/ExpandLess';
import ExpandMore from '@material-ui/icons/ExpandMore';

const data =[
	{ kod: "SKARKL00001", opis: "opis", stan: 458, jm: "szt" },
	{ kod: "SKARKL00002", opis: "opis", stan: 55, jm: "szt" },
	{ kod: "SKARKL00003", opis: "opis", stan: 55, jm: "szt" },
	{ kod: "SKARKL00004", opis: "opis", stan: 55, jm: "szt" },

];

const columns = [
	{ Header: 'Kod', accessor: 'kod', sortType: 'basic' },
	{ Header: 'Opis', accessor: 'opis', sortType: 'basic' },
	{ Header: 'Stan', accessor: 'stan', sortType: 'alphanumeric' },
	{ Header: 'Jm', accessor: 'jm', sortType: 'basic' },
];

const IndeterminateCheckbox = React.forwardRef(
  ({ indeterminate, ...rest }, ref) => {
    const defaultRef = React.useRef()
    const resolvedRef = ref || defaultRef

    React.useEffect(() => {
      resolvedRef.current.indeterminate = indeterminate
    }, [resolvedRef, indeterminate])

    return (
      <>
        <input type="checkbox" ref={resolvedRef} {...rest} />
      </>
    )
  }
)

export default function ThingsTable()
{
	const dataMemo = React.useMemo(()=> data, []);
	const columnMemo = React.useMemo(() => columns, []);
	return (
		<Table columns={columnMemo} data={dataMemo}/>
	);
}

function Table({ columns, data }) {
  // Use the state and functions returned from useTable to build your UI
  const { getTableProps, getTableBodyProps, headerGroups, rows, prepareRow} = useTable({
    columns,
    data,
  }, useSortBy, useRowSelect,
	  hooks =>
	  {
		  hooks.visibleColumns.push(columns => [
			  {
				id: 'selection',
				Header: ({ getToggleAllRowsSelectedProps }) => (
					<div>
						  <IndeterminateCheckbox {...getToggleAllRowsSelectedProps()} />
				  	</div>
				),
				Cell: ({ row }) => (
            <div>
              <IndeterminateCheckbox {...row.getToggleRowSelectedProps()} />
            </div>
          ),
        },
        ...columns,
	  ])
  })

  // Render the UI for your table
  return (
    <MaUTable {...getTableProps()}>
      <TableHead>
        {headerGroups.map(headerGroup => (
          <TableRow {...headerGroup.getHeaderGroupProps()}>
            {headerGroup.headers.map(column => (
				<TableCell {...column.getHeaderProps(column.getSortByToggleProps())}>
					{column.render('Header')}
					<span>
						{column.isSorted ? (column.isSortedDesc ? <ExpandMore/> : <ExpandLess/>) :''}
					</span>
              </TableCell>
            ))}
          </TableRow>
        ))}
      </TableHead>
      <TableBody {...getTableBodyProps()}>
        {rows.map((row, i) => {
          prepareRow(row)
          return (
            <TableRow {...row.getRowProps()}>
              {row.cells.map(cell => {
                return (
                  <TableCell {...cell.getCellProps()}>
                    {cell.render('Cell')}
                  </TableCell>
                )
              })}
            </TableRow>
          )
        })}
      </TableBody>
    </MaUTable>
  )
}
