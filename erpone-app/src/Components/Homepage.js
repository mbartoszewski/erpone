import React, { Component } from 'react';
import { Link } from "react-router-dom"

export default class Homepage extends Component
{
	render()
	{
		return (<div className="Homepage">
			<button className="SignIn">
				<Link to="/signin">Sign In</Link>
			</button>
		</div>)
	}
}