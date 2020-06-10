import React, { Component } from 'react';
import { Route, BrowserRouter as Router } from "react-router-dom";
import Homepage from "./Components/Homepage";
import SignIn from "./Components/SignIn";
import SignUp from "./Components/SignUp";
import Dashboard from "./Components/Dashboard";

class App extends Component
{
  render()
  {
    return (
      <Router>
        <Route exact path="/" component={Homepage} />
        <Route exact path="/signin" component={SignIn} />
        <Route exact path="/signup" component={SignUp} />
        <Route exact path="/dashboard" component={Dashboard} />
      </Router>
    );
  }
}

export default App;
