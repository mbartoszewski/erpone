import { Grid } from "@mui/material";
import React from "react";
import DashboardTile from "../../Components/DashboardTile"
import CompanySettings from "./CompanySettings";
import UserSettings from "./UserSettings";
const Settings = () =>
{
	return (
		<Grid container spacing={2}>
				<Grid item xs={12} md={3} xl={6}>
						<DashboardTile
						title={"Account:"}
					subHeader={"Ustawienia dotyczące obecnie zalogowanego użytkownika. Pojawi się tu hasło, email, numer telefonu."}
				component={<UserSettings />} />
				</Grid>
				<Grid item xs={12} md={6} xl={6}>
					<DashboardTile
						title={"Manage employee account:"}
						subHeader={ "autohide if logged user role !== Admin. Pojawi się lista użytkowników w firmie. Można Aktywować użytkownika, zmienić mu email, nadać hasło oraz zmieniać jego role i uprawnienia."}/>
				</Grid>
				<Grid item xs={12} md={4} xl={4}>
						<DashboardTile
					title={"Company details:"}
					subHeader={"autohide if logged user role !== Admin"}
					component={ <CompanySettings/>}/>
				</Grid>
				<Grid item xs={12} md={4} xl={4}>
						<DashboardTile
					title={"Company details:"}
					subHeader={"autohide if logged user role !== Admin"}
					component={ <CompanySettings/>}/>
			</Grid>
			<Grid item xs={12} md={4} xl={4}>
						<DashboardTile
					title={"Company warehouses:"}
					subHeader={"autohide if logged user role !== Admin || Director"}
					component={ <CompanySettings/>}/>
			</Grid>
			</Grid>
	);
}

export default Settings;