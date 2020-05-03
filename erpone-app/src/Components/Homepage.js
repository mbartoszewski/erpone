import React from "react";
import { Link } from "react-router-dom"
export default function Homepage()
{
	return (
		<div className="Homepage">
			<button className="thingsNavigate">
				<Link to="/warehouses/things">Things</Link>
			</button>
		</div>
	);
}