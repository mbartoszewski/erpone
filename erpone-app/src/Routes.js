import React from 'react';
import { BrowserRouter, Route, Switch } from "react-router-dom";
import Homepage from "./Pages/Homepage";
import SignIn from "./Pages/SignIn";
import SignUp from "./Pages/SignUp";
import Things from './Pages/Warehouse/Things';
import PurchaseOrders from './Pages/Purchase/PurchaseOrders';
import ErpOneApp from './Components/ErpOneApp';
import ThingsDetails from './Pages/Warehouse/ThingsDetails';
import Prices from './Pages/Warehouse/Prices';
import Stocks from './Pages/Warehouse/Stocks';

import PurchaseJs from './Components/Purchase/Purchase';

function Routes() {
  return (
    <BrowserRouter>
      <Route path="/" exact component={Homepage} />
      <Route path="/signin" component={SignIn} />
      <Route path="/signup" component={SignUp} />
      <Route render={(props) => (
        <ErpOneApp {...props}>
          <Switch>
            <Route exact path="/warehouse/things" component={Things} />
            <Route exact path="/purchase/orders" component={PurchaseOrders} />
            <Route exact path="/production/orders" component={PurchaseJs} />
            <Route exact path="/warehouse/things/:id" component={ThingsDetails} />
            <Route exact path="/warehouse/things/:id/stocks" component={Stocks} />
            <Route exact path="/warehouse/things/:id/prices" component={Prices} />
            <Route exact path="/warehouse/things/:id/documents" component={Things} />
          </Switch>
        </ErpOneApp>
      )} />
    </BrowserRouter>
  );
  }

export default Routes;
