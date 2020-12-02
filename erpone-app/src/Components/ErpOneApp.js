import React from 'react';
import Sidebar from "./MenuDrawer/Sidebar";

function ErpOneApp(props)
{
	return (
		<div>
            <div style={{display: "flex"}}>
                <Sidebar history={props.history}/>
                <div style={{width: '100vh', flexGrow: 1}}>
                    {props.children}
                </div>
            </div>
        </div>
	);
}

export default ErpOneApp;