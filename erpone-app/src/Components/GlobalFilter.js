import React from 'react'

import InputBase from '@mui/material/InputBase'
import PropTypes from 'prop-types'
import { alpha} from '@mui/system';
import SearchIcon from '@mui/icons-material/Search'
import { styled, } from '@mui/material/styles';

const Search = styled('div')(({ theme }) => ({
  position: 'relative',
    borderRadius: theme.shape.borderRadius,
    backgroundColor: alpha(theme.palette.common.white, 0.15),
    '&:hover': {
      backgroundColor: alpha(theme.palette.common.white, 0.25),
    },
    marginLeft: 0,
    width: '100%',
    [theme.breakpoints.up('sm')]: {
      width: 'auto',
    },
}));
const SearchIconStyled = styled('div')(({ theme }) => ({
    width: theme.spacing(7),
    height: '100%',
    position: 'absolute',
    pointerEvents: 'none',
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center',
}));

const GlobalFilter = ({
  preGlobalFilteredRows,
  globalFilter,
  setGlobalFilter,
}) => {
  //const classes = useStyles()
  const count = preGlobalFilteredRows.length

  // Global filter only works with pagination from the first page.
  // This may not be a problem for server side pagination when
  // only the current page is downloaded.

  return (
    <Search>
      <SearchIconStyled > 
        <SearchIcon/>
      </SearchIconStyled>
      <InputBase
        value={globalFilter || ''}
        onChange={e => {
          setGlobalFilter(e.target.value || undefined) // Set undefined to remove the filter entirely
        }}
        placeholder={`${count} records...`}
        sx={{
          color: 'inherit',
          p: (theme) => theme.spacing(1, 1, 1, 7),
          transition: (theme) => theme.transitions.create('width'),
          width: '100%',
        }}
        inputProps={{ 'aria-label': 'search' }}
      />
    </Search>
  )
}

GlobalFilter.propTypes = {
  preGlobalFilteredRows: PropTypes.array.isRequired,
  globalFilter: PropTypes.string.isRequired,
  setGlobalFilter: PropTypes.func.isRequired,
}

export default GlobalFilter
