import React from 'react';
import Sidebar from "./MenuDrawer/Sidebar";

const ErpOneApp = (props) =>
{
    return (
        <globalStateContext.Provider value={"test"}>
            <div style={{display: "flex"}}>
                <Sidebar history={props.history}/>
                <div style={{width: '100vh', flexGrow: 1}}>
                    {props.children}
                </div>
            </div>
        </globalStateContext.Provider>          
)
}
export const globalStateContext = React.createContext();
export default ErpOneApp;