import React from 'react';
import { BrowserRouter, Route, Switch } from "react-router-dom";
import Homepage from "./Pages/Homepage";
import SignIn from "./Pages/SignIn";
import SignUp from "./Pages/SignUp";
import Things from './Pages/Warehouse/ThingsPage/Things';
import WarehouseDocuments from './Pages/Warehouse/DocumentsPage/Documents';
import PurchaseOrders from './Pages/Purchase/PurchaseOrders';
import ErpOneApp from './Pages/ErpOneApp';
import ThingsDetails from './Pages/Warehouse/ThingsPage/ThingsDetails';
import Prices from './Pages/Warehouse/Prices';
import Stocks from './Pages/Warehouse/Stocks';
import WarehouseDocumentsDetails from './Pages/Warehouse/DocumentsPage/DocumentsDetails';
import PurchaseJs from './Pages/Purchase/Purchase';

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
            <Route exact path="/warehouse/things/:id/documents" component={WarehouseDocuments} />
            <Route exact path="/warehouse/documents" component={WarehouseDocuments} />
            <Route exact path="/warehouse/documents/:id/details" component={WarehouseDocumentsDetails} />
          </Switch>
        </ErpOneApp>
      )} />
    </BrowserRouter>
  );
  }

export default Routes;
