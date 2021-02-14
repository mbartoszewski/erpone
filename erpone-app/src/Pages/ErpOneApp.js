import React from 'react';
import Sidebar from "./MenuDrawer/Sidebar";
import { apiStates, useApi } from '../Components/Fetch'

const ErpOneApp = (props) =>
{

const { state: stateUnits, error: errorUnits, data: dataUnits } = useApi('http://localhost:5000/api/units/');
const { state: stateWarehouses, error: errorWarehouses, data: dataWarehouses } = useApi('http://localhost:5000/api/warehouses/');
const { state: stateCurrency, error: errorCurrency, data: dataCurrency } = useApi('http://localhost:5000/api/currencies/');

    
    return (
        <globalStateContext.Provider value={{ dataUnits, dataWarehouses, dataCurrency }}>
            {console.log(dataUnits.content)}
            <div style={{display: "flex"}}>
                <Sidebar history={props.history}/>
                <div style={{width: '100vh', flexGrow: 1}}>
                    {props.children}
                </div>
            </div>
        </globalStateContext.Provider>          
)
}

export const globalStateContext = React.createContext("test");
export default ErpOneApp;