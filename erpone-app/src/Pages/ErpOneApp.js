import React from 'react';
import Sidebar from "./MenuDrawer/Sidebar";
import { apiStates, useApi } from '../Components/Fetch'
import Toolbar from '@material-ui/core/Toolbar';
import clsx from 'clsx';
import { makeStyles } from '@material-ui/core/styles';
import CssBaseline from '@material-ui/core/CssBaseline';
import Box from '@material-ui/core/Box';
import AppBar from '@material-ui/core/AppBar';
import Typography from '@material-ui/core/Typography';
import IconButton from '@material-ui/core/IconButton';
import Container from '@material-ui/core/Container';
import Link from '@material-ui/core/Link';
import MenuIcon from '@material-ui/icons/Menu';
import ChevronLeftIcon from '@material-ui/icons/ChevronLeft';

function Copyright() {
  return (
    <Typography variant="body2" color="textSecondary" align="center">
      {'Copyright © '}
      <Link color="inherit" href="https://material-ui.com/">
        Erp One
      </Link>{' '}
      {new Date().getFullYear()}
      {'.'}
    </Typography>
  );
}

const drawerWidth = 240;

const useStyles = makeStyles((theme) => ({
  root: {
    display: 'flex',
  },
  toolbar: {
    paddingRight: 24,
    paddingLeft: 4 // keep right padding when drawer closed
  },
  toolbarIcon: {
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'flex-end',
    padding: '0 8px',
    ...theme.mixins.toolbar,
  },
  appBar: {
    zIndex: theme.zIndex.drawer + 1,
    transition: theme.transitions.create(['width', 'margin'], {
      easing: theme.transitions.easing.sharp,
      duration: theme.transitions.duration.leavingScreen,
    }),
  },
  appBarShift: {
    marginLeft: drawerWidth,
    width: `calc(100% - ${drawerWidth}px)`,
    transition: theme.transitions.create(['width', 'margin'], {
      easing: theme.transitions.easing.sharp,
      duration: theme.transitions.duration.enteringScreen,
    }),
  },
  	menuButton: {
    marginRight: 36,
  },
  	menuButtonHidden: {
    display: 'none',
	},
		closeButton: {
    marginRight: 36,
  },
  	closeButtonHidden: {
    display: 'none',
	},
  title: {
    flexGrow: 1,
  },
  drawerPaper: {
    position: 'relative',
    whiteSpace: 'nowrap',
    width: drawerWidth,
    transition: theme.transitions.create('width', {
      easing: theme.transitions.easing.sharp,
      duration: theme.transitions.duration.enteringScreen,
    }),
  },
  drawerPaperClose: {
    overflowX: 'hidden',
    transition: theme.transitions.create('width', {
      easing: theme.transitions.easing.sharp,
      duration: theme.transitions.duration.leavingScreen,
    }),
    width: theme.spacing(7),
    [theme.breakpoints.up('sm')]: {
      width: theme.spacing(9),
    },
  },
  appBarSpacer: theme.mixins.toolbar,
  content: {
    flexGrow: 1,
    height: '100vh',
    overflow: 'auto',
  },
  container: {
    paddingTop: theme.spacing(2),
    paddingBottom: theme.spacing(4),
    paddingRight: theme.spacing(2),
    paddingLeft: theme.spacing(2),
  },
}));

const ErpOneApp = React.memo((props) =>
{
const classes = useStyles();
const { state: stateUnits, error: errorUnits, data: dataUnits } = useApi('http://localhost:5000/api/units/');
const { state: stateWarehouses, error: errorWarehouses, data: dataWarehouses } = useApi('http://localhost:5000/api/warehouses/');
const { state: stateCurrencies, error: errorCurrencies, data: dataCurrencies } = useApi('http://localhost:5000/api/currencies/');
const { state: statepaymentTerms, error: errorpaymentTerms, data: dataPaymentTerms } = useApi('http://localhost:5000/api/payments/terms/');
const { state: statepaymentForms, error: errorpaymentForms, data: dataPaymentForms } = useApi('http://localhost:5000/api/payments/forms/');

const [isOpen, setIsOpen] = React.useState(false);
const handleDrawerOpen = () => {
    setIsOpen(true);
  };
const handleDrawerClose = () => {
    setIsOpen(false);
  };
    
    return (
        <globalStateContext.Provider value={{ dataUnits, dataWarehouses, dataCurrencies, dataPaymentForms, dataPaymentTerms }}>
            <div className={classes.root} >
                <CssBaseline />
                <AppBar position="absolute" className={classes.appBar}>
                    <Toolbar className={classes.toolbar}>
                        	<IconButton
                            color="inherit"
                            aria-label="open drawer"
                            onClick={handleDrawerOpen}
                            className={clsx(classes.menuButton, isOpen && classes.menuButtonHidden)}
                          >
                            <MenuIcon />
                          </IconButton>
                          <IconButton
                            color="inherit"
                            aria-label="close drawer"
                            onClick={handleDrawerClose}
                            className={clsx(classes.closeButton, !isOpen && classes.closeButtonHidden)}
                              >
                            <ChevronLeftIcon/>
              </IconButton>
                    </Toolbar>
                </AppBar>
                <Sidebar
                    variant="permanent"
                    classes={{
                        paper: clsx(classes.drawerPaper, !isOpen && classes.drawerPaperClose),
                    }}
                    isOpen={isOpen}
                    history={props.history} 
                >
                   
                </Sidebar>
                <main className={classes.content}>
                    <div className={classes.appBarSpacer} />
                    <Container className={classes.container} maxWidth='xl'>
                        {props.children}
                        <Box pt={4}>
                            <Copyright />
                        </Box>
                    </Container>
                </main>
            </div>
        </globalStateContext.Provider>
    );
})

export const globalStateContext = React.createContext("test");
export default ErpOneApp;