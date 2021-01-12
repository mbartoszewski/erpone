import { useState, useEffect} from 'react'

export const apiStates = {
  LOADING: 'Loading',
  SUCCESS: 'Success',
  ERROR: 'Error',
};

export const useApi = (url, options) =>
{
  const [data, setData] = useState({
    state: apiStates.LOADING,
    error: '',
    data: [{}],
  });

  const setPartData = (partialData) => setData({ ...data, ...partialData });
  const fetchData = async() =>{
  await fetch(url, options)
    .then((response) => response.json())
    .then((data) =>
    {
      setPartData({
        state: apiStates.SUCCESS,
        data: data.content
      });
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