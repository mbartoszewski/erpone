import React from 'react'
import { makeStyles } from '@mui/styles';
import Paper from '@mui/material/Paper'


const root = (theme) => ({
    display: 'flex',
    flexWrap: 'wrap',
    '& > *': {
      margin: theme.spacing(1),
      width: theme.spacing(16),
      height: theme.spacing(16),
    }
})
 const chartPaper = (theme) => ({
		width: theme.spacing(16)
	})

function Stocks()
{
	return (
		<div sx={root}>
			<Paper sx={chartPaper} elevation={3} variant='outlined'>
				<p>Działa</p>
			</Paper>
		</div>
	
	);
};
export default Stocks;