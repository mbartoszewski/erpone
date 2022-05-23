import React from 'react';
import Button from '@mui/material/Button';
import ButtonGroup from '@mui/material/ButtonGroup';
import ClickAwayListener from '@mui/material/ClickAwayListener';
import Grow from '@mui/material/Grow';
import Paper from '@mui/material/Paper';
import Popper from '@mui/material/Popper';
import MenuItem from '@mui/material/MenuItem';
import MenuList from '@mui/material/MenuList';
import { makeStyles } from '@mui/styles';
import clsx from 'clsx';
import { useHistory, Link } from "react-router-dom";

const useStyles = makeStyles(theme => ({
	dropDownMenuHidden: {
	display: "none"
  },
  dropDownMenu: {
    borderTopLeftRadius: 0,
    borderBottomLeftRadius: 0,
    marginLeft: "-1px"
  }
}));

export default function DropDownMenu(props)
{
  const classes = useStyles();
  const [open, setOpen] = React.useState(false);
  const anchorRef = React.useRef(null);
  const [selectedIndex, setSelectedIndex] = React.useState(0);

  /*const handleMenuItemClick = (event, index) =>
  {
    setSelectedIndex(index)
    setOpen(false);
  };
*/
  const handleToggle = () => {
    setOpen((prevOpen) => !prevOpen);
  };

  const handleClose = (event) => {
    if (anchorRef.current && anchorRef.current.contains(event.target)) {
      return;
    }
    setOpen(false);
  };
  return (
    <div>
		<ButtonGroup variant="text" ref={anchorRef} aria-label="split button" className={clsx(classes.dropDownMenu, props.hidden && classes.dropDownMenuHidden)}>
			  <Button
				  color='inherit'
					aria-controls={open ? 'split-button-menu' : undefined}
					aria-expanded={open ? 'true' : undefined}
					aria-label="select merge strategy"
					aria-haspopup="menu"
					onClick={handleToggle}
					startIcon={props.icon !==undefined ? props.icon : null}>{props.buttonTitle !==undefined ? props.buttonTitle : ""}
				  </Button>
        </ButtonGroup>
        <Popper open={open} anchorEl={anchorRef.current} role={undefined} transition disablePortal>
          {({ TransitionProps, placement }) => (
            <Grow
              {...TransitionProps}
              style={{
                transformOrigin: placement === 'bottom' ? 'center top' : 'center bottom',
              }}
            >
              <Paper>
              <ClickAwayListener onClickAway={handleClose}>
                  <MenuList id="split-button-menu">
                    {props.menuOptions.map((option, index) => (
                      <MenuItem
                        key={option.title}
                        onClick={() =>
                        {
                          option.function !== undefined ? option.function() : setOpen(false);
                          setOpen(false);
                         }
                        }
                      >
                        {option.pathname !== undefined ? <Link to={{ pathname: `${option.pathname}`, state: option.state, title: option.title}}>{option.title}</Link> : option.title}
                      </MenuItem>
                    ))}
                  </MenuList>
                </ClickAwayListener>
              </Paper>
            </Grow>
          )}
        </Popper>  
	</div>
  );
};
