import React from 'react';
import { BrowserRouter, Route, Switch } from "react-router-dom";
import Homepage from "./Pages/Homepage";
import SignIn from "./Pages/SignIn";
import SignUp from "./Pages/SignUp";
import Things from './Pages/Warehouse/ThingsPage/Things';
import WarehouseDocuments from './Pages/Warehouse/DocumentsPage/WarehouseDocuments';
import SalesDocuments from './Pages/Sales/DocumentsPage/SalesDocuments';
import PurchaseDocuments from './Pages/Purchase/PurchaseDocumentsPage/PurchaseDocuments';
import PurchaseDocumentsDetails from './Components/DocumentsDetailsPage';
import WarehouseDocumentsDetails from './Components/DocumentsDetailsPage';
import SalesDocumentsDetails from './Components/DocumentsDetailsPage';
import ErpOneApp from './Pages/ErpOneApp';
import ThingsDetails from './Pages/Warehouse/ThingsPage/ThingsDetails';
import Prices from './Pages/Warehouse/Prices';
import Stocks from './Pages/Warehouse/Stocks';
import AddDocument from './Components/DocumentsDetailsPage';
import WarehouseDashboard from './Pages/Warehouse/DashboardPage/WarehouseDashboardPage';
import PurchaseDashboard from './Pages/Purchase/DashboardPage/PurchaseDashboardPage'
import SalesDashboardPage from './Pages/Sales/SalesDashboardPage';
import Settings from './Pages/Settings/Settings';

function Routes() {
  return (
    <BrowserRouter>
      <Route path="/" exact component={Homepage} />
      <Route path="/signin" exact component={SignIn} />
      <Route path="/signup" exact component={SignUp} />
      <Route render={(props) => (
        <Switch>
          <Route exact path="/app">
              <ErpOneApp {...props}>
              </ErpOneApp>
            </Route>
            <Route exact path="/purchase/documents">
              <ErpOneApp {...props}>
                <PurchaseDocuments/>
              </ErpOneApp>
            </Route>
            <Route exact path="/purchase/dashboard">
              <ErpOneApp {...props}>
                <PurchaseDashboard/>
              </ErpOneApp>
            </Route>
            <Route exact path="/purchase/documents/details/:id">
              <ErpOneApp {...props}>
                <PurchaseDocumentsDetails/>
              </ErpOneApp>
            </Route>
            <Route exact path="/production/orders">
              <ErpOneApp {...props}>
              </ErpOneApp>
          </Route>
          <Route exact path="/warehouse/things">
              <ErpOneApp {...props}>
                <Things/>
              </ErpOneApp>
            </Route>
            <Route exact path="/warehouse/things/:id">
              <ErpOneApp {...props}>
                <ThingsDetails/>
              </ErpOneApp>
            </Route>
            <Route exact path="/warehouse/things/:id/stocks">
              <ErpOneApp {...props}>
                <Stocks/>
              </ErpOneApp>
            </Route>
            <Route exact path="/warehouse/things/:id/prices">
              <ErpOneApp {...props}>
                <Prices/>
              </ErpOneApp>
            </Route>
            <Route exact path="/warehouse/things/:id/documents">
              <ErpOneApp {...props}>
                <WarehouseDocuments/>
              </ErpOneApp>
            </Route>
            <Route exact path="/warehouse/documents">
              <ErpOneApp {...props}>
                <WarehouseDocuments/>
              </ErpOneApp>
            </Route>
            <Route exact path="/warehouse/documents/details/:id">
              <ErpOneApp {...props}>
                <WarehouseDocumentsDetails/>
              </ErpOneApp>
            </Route>
            <Route exact path="/warehouse/dashboard">
              <ErpOneApp {...props}>
                <WarehouseDashboard/>
              </ErpOneApp>
            </Route>
            <Route exact path="/documents/add">
              <ErpOneApp {...props}>
                <AddDocument/>
              </ErpOneApp>
            </Route>
            <Route exact path="/sales/dashboard">
              <ErpOneApp {...props}>
                <SalesDashboardPage/>
              </ErpOneApp>
          </Route>
          <Route exact path="/sales/documents">
              <ErpOneApp {...props}>
                <SalesDocuments/>
              </ErpOneApp>
          </Route>
          <Route exact path="/sales/documents/details/:id">
              <ErpOneApp {...props}>
                <SalesDocumentsDetails/>
              </ErpOneApp>
            </Route>
            <Route exact path="/settings">
              <ErpOneApp {...props}>
                <Settings/>
              </ErpOneApp>
            </Route>
          </Switch>
        
      )} />
    </BrowserRouter>
  );
  }

export default Routes;
