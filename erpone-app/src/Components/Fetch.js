import { useState, useEffect, useRef} from 'react'

export const apiStates = {
  LOADING: 'Loading',
  SUCCESS: 'Success',
  ERROR: 'Error',
  EMPTY: 'Empty',
};

export const useApi = (url, options) =>
{
  const [data, setData] = useState({
    state: apiStates.LOADING,
    error: '',
    data: null,
  });
  const setPartData = (partialData) => setData({ ...data, ...partialData });
  const fetchData = async () =>
  {
      await fetch(url, options)
        .then((response) =>
        {
          if (!response.ok)
          {
          setPartData({
            state: apiStates.ERROR,
            error: "Error",
            data: null
          });
          } else return response.json()
        })
    .then((data) =>
    {
    if (data !== null && Object.keys(data)[0] === "content") {
       {console.log("połączenie: " + url + " data content:  " + data.content)}
      setPartData({
        state: apiStates.SUCCESS,
        data: data.content
      });
    } else if (data !== null && Object.keys(data)[0] !== "content")
    {
       {console.log("połączenie: " + url + " data:  " + data)}
      setPartData({
        state: apiStates.SUCCESS,
        data: data
      });
    }
     
    })
    .catch(() =>
    {
      setPartData({
        state: apiStates.ERROR,
        error: "E"
      });
    });
  }
  
  useEffect(() =>
  {
    setPartData({ state: apiStates.LOADING, });
    fetchData();
  }, []); //check if this is the best solution. 
  return data;
}
