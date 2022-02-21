import React from 'react';
import { styled, createTheme, ThemeProvider } from '@mui/material/styles';
import CssBaseline from '@mui/material/CssBaseline';
import MuiDrawer from '@mui/material/Drawer';
import Box from '@mui/material/Box';
import MuiAppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import IconButton from '@mui/material/IconButton';
import Container from '@mui/material/Container';
import Link from '@mui/material/Link';
import MenuIcon from '@mui/icons-material/Menu';
import ChevronLeftIcon from '@mui/icons-material/ChevronLeft';
import Sidebar from "./MenuDrawer/Sidebar";
import { apiStates, useApi } from '../Components/Fetch'

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
const footers = [
 /* {
    title: 'Company',
    description: ['Team', 'History', 'Contact us', 'Locations'],
  }*/
];


const AppBar = styled(MuiAppBar, {
  shouldForwardProp: (prop) => prop !== 'open',
})(({ theme, open }) => ({
  zIndex: theme.zIndex.drawer + 1,
  transition: theme.transitions.create(['width', 'margin'], {
    easing: theme.transitions.easing.sharp,
    duration: theme.transitions.duration.leavingScreen,
  }),
  ...(open && {
    marginLeft: drawerWidth,
    width: `calc(100% - ${drawerWidth}px)`,
    transition: theme.transitions.create(['width', 'margin'], {
      easing: theme.transitions.easing.sharp,
      duration: theme.transitions.duration.enteringScreen,
    }),
  }),
}));
const drawerWidth = 240;

const Drawer = styled(MuiDrawer, { shouldForwardProp: (prop) => prop !== 'open' })(
  ({ theme, open }) => ({
    '& .MuiDrawer-paper': {
      position: 'relative',
      whiteSpace: 'nowrap',
      width: drawerWidth,
      transition: theme.transitions.create('width', {
        easing: theme.transitions.easing.sharp,
        duration: theme.transitions.duration.enteringScreen,
      }),
      boxSizing: 'border-box',
      ...(!open && {
        overflowX: 'hidden',
        transition: theme.transitions.create('width', {
          easing: theme.transitions.easing.sharp,
          duration: theme.transitions.duration.leavingScreen,
        }),
        width: theme.spacing(7),
        [theme.breakpoints.up('sm')]: {
          width: theme.spacing(9),
        },
      }),
    },
  }),
);

const theme = createTheme();


const ErpOneApp = React.memo((props) =>
{
const { state: stateUnits, error: errorUnits, data: dataUnits } = useApi('http://localhost:5000/api/units/');
const { state: stateContractors, error: errorContractors, data: dataContractors } = useApi('http://localhost:5000/api/contractors/');
const { state: stateWarehouses, error: errorWarehouses, data: dataWarehouses } = useApi('http://localhost:5000/api/warehouses/');
const { state: stateCurrencies, error: errorCurrencies, data: dataCurrencies } = useApi('http://localhost:5000/api/currencies/');
const { state: statepaymentTerms, error: errorpaymentTerms, data: dataPaymentTerms } = useApi('http://localhost:5000/api/payments/terms/');
const { state: statepaymentForms, error: errorpaymentForms, data: dataPaymentForms } = useApi('http://localhost:5000/api/payments/forms/');
const { state: stateThings, error: errorThings, data: dataThings } = useApi('http://localhost:5000/api/things/list');

const [open, setOpen] = React.useState(false);
  const toggleDrawer = () => {
    setOpen(!open);
  };
    
    return (
      <globalStateContext.Provider value={{ dataUnits, dataWarehouses, dataCurrencies, dataPaymentForms, dataPaymentTerms, dataContractors, dataThings }}>
        <ThemeProvider theme={theme}>
        <Box sx={{ display: 'flex' }}>
        <CssBaseline />
        <AppBar position="absolute" open={open}>
          <Toolbar
            sx={{
              pr: '24px', // keep right padding when drawer closed
                }}
                id="option-toolbar"
          >
            <IconButton
              edge="start"
              color="inherit"
              aria-label="open drawer"
              onClick={toggleDrawer}
              sx={{
                marginRight: '36px',
                ...(open && { display: 'none' }),
              }}
            >
              <MenuIcon />
                </IconButton>
                <IconButton
                edge="start"
                color="inherit"
                aria-label="close drawer"
                onClick={toggleDrawer}
                sx={{
                marginRight: '36px',
                ...(!open && { display: 'none' }),
              }}>
              <ChevronLeftIcon />
            </IconButton>
          </Toolbar>
        </AppBar>
            <Sidebar open={open}/>
        <Box
          component="main"
          sx={{
            backgroundColor: (theme) =>
              theme.palette.mode === 'light'
                ? theme.palette.grey[50]
                : theme.palette.grey[200],
            flexGrow: 1,
            pt: (theme) =>
              theme.spacing(10),
            pb: (theme) =>
              theme.spacing(1),
            pl: (theme) =>
              theme.spacing(1),
            pr: (theme) =>
              theme.spacing(1),
            height: '100vh',
            overflow: 'auto',
          }}
        >
            {props.children}
        </Box>
      </Box>
    </ThemeProvider>
        </globalStateContext.Provider>
    );
})

export const globalStateContext = React.createContext("test");
export default ErpOneApp;