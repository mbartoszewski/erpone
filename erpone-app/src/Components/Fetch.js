import { useState, useEffect} from 'react'

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
    data: [null],
  });

  const setPartData = (partialData) => setData({ ...data, ...partialData });
  const fetchData = async() =>{
  await fetch(url, options)
    .then((response) => response.json())
    .then((data) =>
    {
    if (data.content.length >= 1) {
       {console.log("połączenie: " + url + " data:  " + data.content)}
      setPartData({
        state: apiStates.SUCCESS,
        data: data.content
      });
    } else
    {
       setPartData({
        state: apiStates.EMPTY,
        error: 'Empty response'
      });
      }
     
    })
    .catch(() =>
    {
      setPartData({
        state: apiStates.ERROR,
        error: 'fetch failed'
      });
    });
  }
  useEffect(() =>
  {
    setPartData({ state: apiStates.LOADING, });
    fetchData();
  }, []);
  return data;
}
