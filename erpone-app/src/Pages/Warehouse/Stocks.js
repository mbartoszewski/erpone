import React from 'react'
import { makeStyles } from '@material-ui/core/styles';
import Paper from '@material-ui/core/Paper'

const useStyles = makeStyles((theme) => ({
	root: {
    display: 'flex',
    flexWrap: 'wrap',
    '& > *': {
      margin: theme.spacing(1),
      width: theme.spacing(16),
      height: theme.spacing(16),
    },
  },
	chartPaper: {
		width: theme.spacing(16)
	}
}))
function Stocks()
{
	const classes = useStyles();
	return (
		<div className={classes.root}>
			<Paper className={classes.chartPaper} elevation={3} variant='outlined'>
				<p>Działa</p>
			</Paper>
		</div>
	
	);
};
export default Stocks;