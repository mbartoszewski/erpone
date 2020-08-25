import React, { useState } from 'react';
import clsx from 'clsx';
import { makeStyles } from '@material-ui/core/styles';
import CssBaseline from '@material-ui/core/CssBaseline';
import Drawer from '@material-ui/core/Drawer';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import IconButton from '@material-ui/core/IconButton';
import Badge from '@material-ui/core/Badge';
import Container from '@material-ui/core/Container';
import MenuIcon from '@material-ui/icons/Menu';
import ChevronLeftIcon from '@material-ui/icons/ChevronLeft';
import NotificationsIcon from '@material-ui/icons/Notifications';
import ListItem from '@material-ui/core/ListItem';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListItemText from '@material-ui/core/ListItemText';
import InboxIcon from '@material-ui/icons/MoveToInbox';
import ExpandLess from '@material-ui/icons/ExpandLess';
import ExpandMore from '@material-ui/icons/ExpandMore';
import AddShoppingCartIcon from '@material-ui/icons/AddShoppingCart';
import DescriptionIcon from '@material-ui/icons/Description';
import FormatListNumberedIcon from '@material-ui/icons/FormatListNumbered';
import DomainIcon from '@material-ui/icons/Domain';
import WorkIcon from '@material-ui/icons/Work';
import MonetizationOnIcon from '@material-ui/icons/MonetizationOn';
import Collapse from '@material-ui/core/Collapse';
import { List, Divider, Tooltip, Zoom} from '@material-ui/core';
import ThingsTable from './Warehouse';
import PurchaseOrders from './Purchase';

const drawerWidth = 240;

const useStyles = makeStyles((theme) => ({
  root: {
    display: 'flex',
  },
  toolbar: {
    paddingRight: 24, // keep right padding when drawer closed
  },
  drawerRoot: {
    width: '100%',
    maxWidth: 360,
    backgroundColor: theme.palette.background.paper,
  },
  nested: {
    paddingLeft: theme.spacing(4),
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
    height: '100vh',
    flexGrow: 1,
   
    overflow: 'auto',
  },
  container: {
    display: 'flex',
    overflow: 'auto',
    flexDirection: 'column',
    padding: 0
  },
  paper: {
    padding: theme.spacing(2),
    display: 'flex',
    overflow: 'auto',
    flexDirection: 'column',
  },
  fixedHeight: {
    height: 240,
  },
}));

export default function Dashboard() {
  const classes = useStyles();
  const [open, setOpen] = useState(false);
  const [navigationItem, setView] = useState("");
  const handleDrawerOpen = () => {
    setOpen(true);
  };
  const handleDrawerClose = () => {
    setOpen(false);
  };
  const [expand, setExpand] = useState([{ id: "1", isExpand: false },
    { id: "2", isExpand: false },
    { id: "3", isExpand: false },
    { id: "4", isExpand: false },
    { id: "5", isExpand: false }]);

  const handleListItemClick = (id) =>
  {
    setExpand(expand.map(expand => expand.id === id ? { ...expand, isExpand: !expand.isExpand } : expand));
  }
  
  const switchView = (component) =>
  {
    switch (component) {
      case "Warehouse":
        return null;
      case "Things":
        return <ThingsTable />;
      case "Orders":
        return <PurchaseOrders/>;
      case "":
        return null;
      default:
        break;
    }
  }
  return (
    <div className={classes.root}>
      <CssBaseline />
      <AppBar position="absolute" className={clsx(classes.appBar, open && classes.appBarShift)}>
        <Toolbar className={classes.toolbar}>
          <IconButton
            edge="start"
            color="inherit"
            aria-label="open drawer"
            onClick={handleDrawerOpen}
            className={clsx(classes.menuButton, open && classes.menuButtonHidden)}
          >
            <MenuIcon />
          </IconButton>
          <Typography id= "HeaderTitle" component="h1" variant="h6" color="inherit" noWrap className={classes.title}>
            
          </Typography>
          <IconButton color="inherit">
            <Badge badgeContent={4} color="secondary">
              <NotificationsIcon />
            </Badge>
          </IconButton>
        </Toolbar>
      </AppBar>
      <Drawer
        variant="permanent"
        classes={{
          paper: clsx(classes.drawerPaper, !open && classes.drawerPaperClose),
        }}
        open={open}
      >
        <div className={classes.toolbarIcon}>
          <IconButton onClick={handleDrawerClose}>
            <ChevronLeftIcon />
          </IconButton>
        </div>
        <Divider />
        <List component="nav" className={classes.drawerRoot}>
      {menuData.map(each => (
        <React.Fragment key={each.id}>
          <ListItem button onClick={() => handleListItemClick(each.id)}>
            <Tooltip title={each.nameHeader} TransitionComponent="zoom" placement="right">
              <ListItemIcon>
                {React.createElement(each.icon)}
              </ListItemIcon>
            </Tooltip>
        <ListItemText primary={each.nameHeader} />
        {expand.find(expand => expand.id === each.id).isExpand ? <ExpandLess /> : <ExpandMore />}
      </ListItem>
      <Collapse in={expand.find(expand => expand.id === each.id).isExpand} timeout="auto" unmountOnExit>
            <List component="div" disablePadding>
              {each.subMenu.map(subMenuData => (
                <ListItem key={subMenuData.id} button onClick={() =>
                {
                  setView(subMenuData.name);
                  document.getElementById('HeaderTitle').innerHTML = each.nameHeader + " > " + subMenuData.name + " " + navigationItem;
                }} className={classes.nested}>
                  <Tooltip title={subMenuData.name} TransitionComponent={Zoom} placement="right">
                    <ListItemIcon>
                      {React.createElement(subMenuData.icon)}
                    </ListItemIcon>
                </Tooltip>
            <ListItemText primary={subMenuData.name} />
                </ListItem>          
              ))}
        </List>
      </Collapse>
          <Divider />
        </React.Fragment>
      ))}
    </List>
      </Drawer>
      <main className={classes.content}>
        <Container maxWidth="xl"  className={classes.container}>
          <div className={classes.appBarSpacer} />
          {switchView(navigationItem)}
        </Container>
      </main>
    </div>
  );
}

const menuData= [
  {
    id: "1",
    nameHeader: "Production",
    icon: WorkIcon,
    subMenu: [{ id: "1", name: "Dashboard", icon: InboxIcon }, { id: "2", name: "Plan", icon: InboxIcon}]
  },
  {
    id: "2",
    nameHeader: "Planning",
    icon: FormatListNumberedIcon,
    subMenu: [{ id: "1", name: "Dashboard", icon: InboxIcon }, { id: "2", name: "subMenu2", icon: InboxIcon }]
  },
  {
    id: "3",
    nameHeader: "Purchase",
    icon: AddShoppingCartIcon,
    subMenu: [{ id: "1", name: "Dashboard", icon: InboxIcon }, { id: "2", name: "Orders", icon: InboxIcon }, { id: "3", name: "Offers", icon: InboxIcon }]
  },
  {
    id: "4",
    nameHeader: "Sales",
    icon: MonetizationOnIcon,
    subMenu: [{ id: "1", name: "Dashboard", icon: InboxIcon }, { id: "2", name: "Orders", icon: InboxIcon}, { id: "3", name: "Offers", icon: InboxIcon }]
  },
  {
    id: "5",
    nameHeader: "Warehouse",
    icon: DomainIcon,
    subMenu: [{ id: "1", name: "Warehouse", icon: InboxIcon }, { id: "2", name: "Things", icon: InboxIcon }, { id: "3", name: "Documents", icon: DescriptionIcon}]
  }
];