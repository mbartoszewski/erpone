export const ReturnYTD = (yearOffset, monthOffset, dayOffset, firstDayOfYear) =>
{
	const date = new Date();
	const fullYear = date.getUTCFullYear();
	const month = date.getUTCMonth();
	const day = date.getUTCDate();
	
	if (yearOffset !== null & yearOffset !== 0)
	{
		date.setUTCFullYear(fullYear + yearOffset)
	}
	if (monthOffset !== null & monthOffset !== 0)
	{
		date.setUTCMonth(month + monthOffset)
	}
	if (dayOffset !== null & dayOffset !== 0)
	{
		date.setUTCDate(day + dayOffset)	
	}
	if (firstDayOfYear !== null & firstDayOfYear === 1)
	{
		date.setUTCMonth(0);
		date.setUTCDate(1);
	}	
	
	return date.toJSON();
}

export const getISODateOfWeek = (w, y) => {
    let simple = new Date(Date.UTC(y, 0, 1 + (w - 1) * 7));
    let dow = simple.getUTCDay();
	let ISOweekStart = simple;
	let dates = [];
    if (dow <= 4)
        ISOweekStart.setUTCDate(simple.getUTCDate() - simple.getUTCDay() + 1);
    else
		ISOweekStart.setUTCDate(simple.getUTCDate() + 8 - simple.getUTCDay());
	
	let day = new Date(Date.UTC(ISOweekStart.getUTCFullYear(), ISOweekStart.getUTCMonth(), ISOweekStart.getUTCDate()))

	for (let i = 1; i <= 7; i++)
	{
		dates.push(day.toISOString().split('T')[0])
		day.setUTCDate(day.getUTCDate() + 1);
	}
	return dates;
}

export const groupByDate = (xs, key) =>
{
	if (xs !== null && xs !== undefined && xs.length > 0)
	{
		return xs.reduce((rv, x) => {
	(rv[x[key].split('T')[0]] = rv[x[key].split('T')[0]] || []).push(x);
	return rv;
	}, {});
		}
	return null;
};

export const transposeJson = (json) =>
{
	let keys = []
	let newObj = []
	let newMap = {}
	let maxLength = 0;

	if (json !== undefined && json !== null && Object.keys(json).length)
	{
		Object.keys(json).forEach((o) =>
		{
			keys.push(o)
			maxLength =  maxLength < json[o].length ? json[o].length : maxLength
		})
		}	
	
	if (json !== undefined && json !== null && Object.keys(json).length)
	{
		for (let i = 0; i < maxLength; i++)
		{		
			
			Object.keys(json).forEach((o) =>
			{	
				if (json[o].find(x => x !== undefined))
				{
					newMap = { ...{ [`${o}`]: json[o].shift() }, ...newMap }
				}
			})
			newObj.push(newMap)
			newMap = {}
		}
	}
	return newObj;
}

export const getISO8601WeekNumberFromDate = (date) =>
{
	var tdt = new Date(date.valueOf());
    var dayn = (date.getDay() + 6) % 7;
    tdt.setDate(tdt.getDate() - dayn + 3);
    var firstThursday = tdt.valueOf();
    tdt.setMonth(0, 1);
    if (tdt.getDay() !== 4) {
        tdt.setMonth(0, 1 + ((4 - tdt.getDay()) + 7) % 7);
    }
    return 1 + Math.ceil((firstThursday - tdt) / 604800000);
}

export const docStates = {
  	ADD: 'Adding',
  	EDIT: 'Editing',
	DRAFT: 'Draft',
	VIEW: 'Viewing'
};

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
		varValues = (firstValue / secondValue)
	}
	return (varValues*100).toFixed(2);
}

export const DocValue = (data) =>
{
	let value = 0;
  if (data != null)
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
	if (data != null)
	{
		data.forEach(d =>
		{
			if (idValueMap != null)
			{
				
				}
		})
	}
	
	return idValueMap;
}

export const pathTo = (ref, data, path = []) => {
  const found = data && Object.entries(data).find(([k,v]) => {
    if (v === ref) return path.push(k)
    if (typeof v === 'object') {
      const tmp = pathTo(ref, v, [...path, k])
      if (tmp) return path = tmp
    }
  })
  if (found) return path
}

export default ReturnYTD;