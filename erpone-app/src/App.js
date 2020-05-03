import React, { Component } from 'react';
import { Route, BrowserRouter as Router } from "react-router-dom";
import Homepage from "./Components/Homepage";
import WarehousesThings from "./Components/WarehousesThings";

class App extends Component
{
  render()
  {
    return (
      <Router>
        <Route exact path="/" component={Homepage} />
        <Route exact path="/warehouses/things" component={WarehousesThings} />
      </Router>
    );
  }
}

export default App;
