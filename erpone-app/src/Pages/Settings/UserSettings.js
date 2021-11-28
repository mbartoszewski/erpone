import { Grid, TextField } from "@mui/material";
import React from "react";
import AccountBoxIcon from '@mui/icons-material/AccountBox';

const UserSettings = () =>
{
	return (
		<form >
			<Grid container spacing={2}>
				<Grid container spacing={2} xs={8} md={8} xl={8}>
					<Grid item xs={12} md={12} xl={12}>
						<TextField required id="user-firstname" label="First name" />
					</Grid>
					<Grid item xs={12} md={12} xl={12}>
						<TextField required id="user-lastname" label="Last name" />
					</Grid>
					<Grid item xs={12} md={12} xl={12}>
						<TextField required id="user-email" label="Email" />
					</Grid>
				</Grid>
				<Grid container spacing={2} xs={4} md={4} xl={4}>
					<Grid item xs={8} md={8} xl={8}>
						<AccountBoxIcon/>
					</Grid>
					<Grid item xs={8} md={8} xl={8}>
						<TextField required id="user-lastname" label="Upload avatar" />
					</Grid>
				</Grid>
			</Grid>
		</form>
	);
}

export default UserSettings;