import React from 'react'
import useFetch from '../../Components/Fetch';

const PurchaseOrder = () =>
{
  const data = useFetch("http://localhost:5000/api/things/");
  return (
    <div>
     {data}
    </div>
  )
}
export default PurchaseOrder
