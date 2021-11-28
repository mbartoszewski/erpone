import { Grid, TextField } from "@mui/material";
import React from "react";

const CompanySettings = () =>
{
	return (
		<form >
			<Grid container spacing={2}>
				<Grid item xs={8} md={8} xl={8}>
					<TextField required id="company-name" label="Company name" />
				</Grid>
				<Grid item xs={8} md={8} xl={8}>
					<TextField required id="company-vat-number" label="Vat number" />
				</Grid>
				<Grid item xs={8} md={8} xl={8}>
					<TextField required id="company-street" label="Street" />
				</Grid>
				<Grid item xs={8} md={8} xl={8}>
					<TextField required id="company-house" label="House number" />
				</Grid>
				<Grid item xs={8} md={8} xl={8}>
					<TextField required id="company-city" label="City" />
				</Grid>
				<Grid item xs={8} md={8} xl={8}>
					<TextField required id="company-postal-code" label="Postal code" />
				</Grid>
				<Grid item xs={8} md={8} xl={8}>
					<TextField required id="company-country" label="Country" />
				</Grid>
				<Grid item xs={8} md={8} xl={8}>
					<TextField required id="company-default-currency" label="Currency" />
				</Grid>
			</Grid>
				
				
				
				
				
				
			
		</form>
	);
}

export default CompanySettings;