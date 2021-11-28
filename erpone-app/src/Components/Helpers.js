import React from "react";
export const ReturnYTD = (yearOffset, monthOffset, dayOffset, firstDayOdYear) =>
{
	const date = new Date();
	const fullYear = date.getFullYear();
	const month = date.getMonth();
	const day = date.getDate();
	
	if (yearOffset !== null & yearOffset !== 0)
	{
		date.setFullYear(fullYear + yearOffset)
	}
	if (monthOffset !== null & monthOffset !== 0)
	{
		date.setMonth(month + monthOffset)
	}
	if (dayOffset !== null & dayOffset !== 0)
	{
		date.setDate(day + dayOffset)	
	}
	if (firstDayOdYear !== null & firstDayOdYear === 1)
	{
		date.setMonth(0);
		date.setDate(1);
	}	
	
	return date.toJSON();
}

export const UnitConverter = (value) =>
{
	if (value < 100000)
	{
		return value.toFixed(1) + " €"
	}
	if (value >= 100000 & value <= 999999)
	{
		return (value/1000).toFixed(0) + " k €"
	}
	if (value > 999999 & value <= 999999999)
	{
		return (value/1000000).toFixed(2) + " mln €"
	}
	if (value > 999999999)
	{
		return (value/1000000000).toFixed(3) + " bln €"
		}
	return value;
}

export const VariationCalculator = (firstValue, secondValue) =>
{
	let varValues = 0;
	if (firstValue > 0 & secondValue > 0)
	{
		if (firstValue > secondValue)
	{
		varValues = (firstValue / secondValue)
	} else
	{
		varValues = -(secondValue / firstValue)
	}
	
	if (varValues > 1)
	{
		return ((varValues - 1) * 100).toFixed(2);
	} else if (varValues < -1)
	{
		return ((varValues + 1) * 100).toFixed(2);
	}else
	{
		return (varValues*100).toFixed(2);
		}
		}
	return 0;
}

export const DocValue = (data) =>
{
	let value = 0;
  if (data !== null)
  {
    data.map(v =>
    {
      value += v.docValue
    });
    return value;
  } else
    return 0;
}

export const DocValueByProperties = (data, properties) =>
{
	let idValueMap = {}
	if (data !== null)
	{
		data.forEach(d =>
		{
			if (idValueMap !== null)
			{
				
				}
		})
	}
	
	return idValueMap;
}

export default ReturnYTD;