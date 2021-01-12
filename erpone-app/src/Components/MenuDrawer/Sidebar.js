import React, { useState } from 'react';
import clsx from 'clsx';
import { Link } from "react-router-dom";
import { makeStyles } from '@material-ui/core/styles';
import Collapse from '@material-ui/core/Collapse';
import Drawer from '@material-ui/core/Drawer';
import { List, Divider, Tooltip, Zoom } from '@material-ui/core';
import ListItem from '@material-ui/core/ListItem';
import SidebarItems from "./SidebarItems";
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListItemText from '@material-ui/core/ListItemText';
import ExpandLess from '@material-ui/icons/ExpandLess';
import ExpandMore from '@material-ui/icons/ExpandMore';
import ChevronLeftIcon from '@material-ui/icons/ChevronLeft';
import IconButton from '@material-ui/core/IconButton';
import MenuIcon from '@material-ui/icons/Menu';

const drawerWidth = 240;

const useStyles = makeStyles((theme) => ({
  nested: {
    paddingLeft: theme.spacing(4),
	},
  drawerRoot: {
    width: '100%',
    maxWidth: 360,
    backgroundColor: theme.palette.background.paper,
  },
  SidebarParent: {
		background: '#ffffff',
    borderRight: '1px solid rgba(0, 0, 0, 0.12)'
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
  drawerPaper: {
    position: 'relative',
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
}));

function Sidebar({defaultActive})
{
	const classes = useStyles();
	const [expand, setExpand] = useState([{ id: "1", isExpand: false },
    { id: "2", isExpand: false },
    { id: "3", isExpand: false },
    { id: "4", isExpand: false },
    { id: "5", isExpand: false }]);
	const [open, setOpen] = useState(false);
  const handleDrawerOpen = () => {
    setOpen(true);
  };
  const handleDrawerClose = () => {
    setOpen(false);
  };
  const handleListItemClick = (id) =>
  {
    setExpand(expand.map(expand => expand.id === id ? { ...expand, isExpand: !expand.isExpand } : expand));
  }
  return (
    <>
		  <div className={classes.SidebarParent}>
        <Drawer variant="permanent" classes={{ paper: clsx(classes.drawerPaper, !open && classes.drawerPaperClose), }} open={open} >
        <div>
			<IconButton
            color="inherit"
            aria-label="open drawer"
            onClick={handleDrawerOpen}
            className={clsx(classes.menuButton, open && classes.menuButtonHidden)}
          >
            <MenuIcon />
			</IconButton>
			<IconButton
            color="inherit"
            aria-label="close drawer"
            onClick={handleDrawerClose}
            className={clsx(classes.closeButton, !open && classes.closeButtonHidden)}
          >
            <ChevronLeftIcon/>
        </IconButton>
        </div>
        <Divider />
		<List component="nav" className={classes.drawerRoot}>
		{SidebarItems.map(each => (
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
				<Divider/>
		<Collapse in={expand.find(expand => expand.id === each.id).isExpand} timeout="auto" unmountOnExit>
				<List component="div" disablePadding>
            {each.subMenu.map(subMenuData => (
          <Link to={subMenuData.route} className={classes.a}>
					<ListItem key={subMenuData.id} className={classes.nested}>
					<Tooltip title={subMenuData.name} TransitionComponent={Zoom} placement="right">
						<ListItemIcon>
						{React.createElement(subMenuData.icon)}
						</ListItemIcon>
					</Tooltip>
				<ListItemText primary={subMenuData.name} />
                </ListItem> 
          </Link>      
				))}
			</List>
		</Collapse>
			</React.Fragment>
		))}
      </List>
		</Drawer>		  
	</div>
      <div style={{maxWidth: '240px'}}/>
      </>
);
}

export default Sidebar;