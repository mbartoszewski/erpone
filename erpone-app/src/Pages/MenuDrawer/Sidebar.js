import React, { useState } from 'react';
import { useHistory, Link } from "react-router-dom";
import Collapse from '@mui/material/Collapse';
import { List, Divider, Tooltip, Zoom } from '@mui/material';
import { styled, createTheme, ThemeProvider } from '@mui/material/styles';
import MuiDrawer from '@mui/material/Drawer';
import ListItem from '@mui/material/ListItem';
import SidebarItems from "./SidebarItems";
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import ExpandLess from '@mui/icons-material/ExpandLess';
import ExpandMore from '@mui/icons-material/ExpandMore';
import Toolbar from '@mui/material/Toolbar';

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
        width: theme.spacing(9),
        [theme.breakpoints.up('sm')]: {
          width: theme.spacing(9),
        },
      }),
    },
  }),
);
const DrawerTitle = styled(ListItemText, { shouldForwardProp: (prop) => prop !== 'open' })(
  ({ open }) => ({
    display: 'block',
    margin: '0',
    ...(!open && { display: 'none'})
  })
);
const mdTheme = createTheme();

const Sidebar = ({open}) =>
{
  const history = useHistory();
	const [expand, setExpand] = useState([{ id: "1", isExpand: false, expandable: true },
    { id: "2", isExpand: false, expandable: true },
    { id: "3", isExpand: false, expandable: true },
    { id: "4", isExpand: false, expandable: true },
    { id: "5", isExpand: false, expandable: true },
    { id: "6", isExpand: false, expandable: false }]);

  const handleListItemClick = (id) =>
  {
    setExpand(expand.map(expand => expand.id === id ? { ...expand, isExpand: !expand.isExpand } : expand));
  }
  return (
    <ThemeProvider theme={mdTheme}>
<Drawer variant="permanent" open={open}>
      <Divider />
          <Toolbar
            sx={{
              display: 'flex',
              alignItems: 'center',
              justifyContent: 'flex-end',
              px: [1],
            }}
          >
          </Toolbar>
      		<List component="nav" >
		{SidebarItems.map(each => (
			<React.Fragment key={each.id}>
			<ListItem key={each.id} button onClick={() => each.route != null?history.push(each.route):handleListItemClick(each.id)}>
				<Tooltip title={each.nameHeader} TransitionComponent={Zoom} placement="right" arrow>
          <ListItemIcon>
            {React.createElement(each.icon)}
          </ListItemIcon>
        </Tooltip>
          <DrawerTitle primary={each.nameHeader} open={open}/>
			    { expand.find(expand => expand.id === each.id).expandable ? expand.find(expand => expand.id === each.id).isExpand ? <ExpandLess /> : <ExpandMore /> : null}
				</ListItem>
				<Divider/>
        <Collapse in={expand.find(expand => expand.id === each.id).isExpand} timeout="auto" unmountOnExit>
            <List component="div" disablePadding>
                {each.subMenu != null ? each.subMenu.map(subMenuData => (
              <Link key={subMenuData.id} to={subMenuData.route} >
                    <ListItem key={subMenuData.id} sx={{paddingLeft: (theme) => theme.spacing(4)}}>
              <Tooltip title={subMenuData.name} TransitionComponent={Zoom} placement="right" arrow>
                <ListItemIcon>
                    {React.createElement(subMenuData.icon)}
                </ListItemIcon>
            </Tooltip>
            <ListItemText primary={subMenuData.name} />
                    </ListItem> 
              </Link>      
            )): false}
          </List>
        </Collapse>
			</React.Fragment>
		))}
      </List>
      </Drawer>
         </ThemeProvider>
);
}

export default Sidebar;