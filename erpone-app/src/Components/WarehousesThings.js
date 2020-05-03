import React from "react";
import Table from "@material-ui/core/Table";
import TableBody from "@material-ui/core/TableBody";
import TableCell from "@material-ui/core/TableCell";
import TableContainer from "@material-ui/core/TableContainer";
import TableHead from "@material-ui/core/TableHead";
import TableRow from "@material-ui/core/TableRow";
export default function WarehousesThings()
{

	const [data, upDateData] = React.useState([]);
	const [firstLoad, setLoad] = React.useState(true);
	let isLoading = false;
	async function getThings()
	{
		let response = await fetch("/warehouses/things");
		let body = await response.json();
		upDateData(body);
	}
	if (firstLoad)
	{
		getThings();
		setLoad(false);
	}
	if (data.length > 0) isLoading = false;

	return (
		<div className="WarehousesThings">
			{isLoading ? (<p>loading</p>) :
				(
					<TableContainer>
						<Table className="things">
							<TableHead>
								<TableRow>
									<TableCell>Code</TableCell>
									<TableCell>Name</TableCell>
									<TableCell>Quantity</TableCell>
									<TableCell>Unit</TableCell>
								</TableRow>
							</TableHead>
							<TableBody>
								{data?.map(row => (
									<TableRow key={row.name}>
										<TableCell>{row.name}</TableCell>
									</TableRow>))}
							</TableBody>
						</Table>
					</TableContainer>)}
		</div>
	);
}