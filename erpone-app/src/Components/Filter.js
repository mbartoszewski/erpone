import React from "react";
import { Checkbox, FormControl, FormControlLabel, FormGroup, FormLabel, Paper } from "@mui/material";

const Filter = (props) =>
{
	
	const handleCheckButtonClick = (event) =>
	{
		
		}
	return (
		<Paper sx={{minHeight: "100px", height: "100%"}} variant="outlined" elevation={2}>
			{props.filterData.map(each => 
			(<React.Fragment key={each.filterName}>
				<FormControl>
					<FormLabel component="legend">{each.filterName}</FormLabel>
					<FormGroup>
						{each.optionList.map(item => (
							<FormControlLabel control={<Checkbox />} name="test" label={item.option} />
						))}
					</FormGroup>
				</FormControl>
			</React.Fragment>))}
			
		</Paper>
	);
}

export default Filter;