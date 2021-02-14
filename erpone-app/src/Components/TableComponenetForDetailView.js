import React from 'react'
import { makeStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import { __RouterContext } from 'react-router';

const useStyles = makeStyles((theme) => ({
}))
const TableComponent = ({ title, columns, data }) =>
{
	const classes = useStyles();
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
					<TableBody>
						{data.map((d) => (
							<TableRow>
								{columns.map((c) => (
									<TableCell>
									{c.accessor.split('.').reduce((a, b) => a[b], d)}
									</TableCell>
								))}	
							</TableRow>
						))}
					</TableBody>
				</Table>
			</TableContainer>
		</div>
	);
}
export default TableComponent