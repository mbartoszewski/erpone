import React from 'react';
import { BrowserRouter, Route, Routes } from "react-router-dom";
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
import SalesBudget from './Pages/Sales/SalesBudget';

function RoutesHandler() {
 return (
  <BrowserRouter>
      <Routes>
      <Route path="/" element={<Homepage/>} />
      <Route path="/signin" element={<SignIn/>} />
      <Route path="/signup" element={<SignUp/>} />
      <Route path="/app" element={<ErpOneApp/>}/>
      <Route path="/documents/add" element={ <ErpOneApp> <AddDocument/> </ErpOneApp>}/>
      <Route path="/purchase/documents" element={ <ErpOneApp> <PurchaseDocuments/> </ErpOneApp>}/>
      <Route path="/purchase/dashboard" element={ <ErpOneApp> <PurchaseDashboard/> </ErpOneApp>}/>
      <Route path="/purchase/documents/details/:id" element={ <ErpOneApp> <PurchaseDocumentsDetails/> </ErpOneApp>}/>
      <Route path="/production/orders" element={ <ErpOneApp>  </ErpOneApp>} />
      <Route path="/warehouse/things" element={ <ErpOneApp> <Things /> </ErpOneApp>}/>
      <Route path="/warehouse/things/:id" element={ <ErpOneApp> <ThingsDetails /> </ErpOneApp>} />
      <Route path="/warehouse/things/:id/stocks" element={ <ErpOneApp> <Stocks/> </ErpOneApp>}/>
      <Route path="/warehouse/things/:id/prices" element={ <ErpOneApp> <Prices/> </ErpOneApp>}/>
      <Route path="/warehouse/things/:id/documents" element={ <ErpOneApp> <WarehouseDocuments/> </ErpOneApp>}/>
      <Route path="/warehouse/documents" element={ <ErpOneApp> <WarehouseDocuments/> </ErpOneApp>}/>
      <Route path="/warehouse/documents/details/:id" element={ <ErpOneApp> <WarehouseDocumentsDetails/> </ErpOneApp>}/>
      <Route path="/warehouse/dashboard" element={<ErpOneApp><WarehouseDashboard/> </ErpOneApp>}/>
      <Route path="/sales/dashboard" element={ <ErpOneApp> <SalesDashboardPage/> </ErpOneApp>}/>
      <Route path="/sales/documents" element={ <ErpOneApp> <SalesDocuments/> </ErpOneApp>}/>
      <Route path="/sales/budget" element={  <ErpOneApp> <SalesBudget/> </ErpOneApp>}/>
      <Route path="/sales/documents/details/:id" element={  <ErpOneApp> <SalesDocumentsDetails/> </ErpOneApp>}/>
      <Route path="/settings" element={ <ErpOneApp> <Settings/> </ErpOneApp>}/>
    </Routes>
  </BrowserRouter>
 );
 }

export default RoutesHandler;
