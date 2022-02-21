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
    data: null,
  });
  const fetchData = async () =>
  {
      await fetch(url, options)
        .then((response) =>
        {
          if (!response.ok)
          {
          setData({
            state: apiStates.ERROR,
            error: "Error",
            data: null
          });
          } else return response.json()
        })
    .then((data) =>
    {
    if (data !== null) {
      setData({
        state: apiStates.SUCCESS,
        error: '',
        data: Object.keys(data)[0] === "content" ? data.content : data
      });
    }
     
    })
    .catch((e) =>
    {
      setData({
        state: apiStates.ERROR,
        error: e.message,
        data: null
      });
    });
  }
  
  useEffect(() =>
  {
    setData({ state: apiStates.LOADING,  error: '', data: null});
    fetchData();
  }, []); //check if this is the best solution. 
  return data;
}

export const useApii = (initialUrl, initialOptions) =>
{
  const [data, setData] = useState({
    state: apiStates.LOADING,
    error: '',
    data: {},
  });
  
  const [callProperties, setCallProperties] = useState({url: initialUrl, options: initialOptions})
  const fetchData = async () =>
  {
      await fetch(callProperties.url, callProperties.options)
        .then((response) =>
        {
          if (!response.ok)
          {
          setData({
            state: apiStates.ERROR,
            error: 'Error, response not ok',
            data: null
          });
          } else return response.json()
        })
    .then((data) =>
    {
    if (data !== null) {
      setData({
        state: apiStates.SUCCESS,
        error: '',
        data: Object.keys(data)[0] === "content" ? data.content : data
      });
    }
     
    })
    .catch((e) =>
    {
      setData({
        state: apiStates.ERROR,
        error: e.message,
        data: null
      });
    });
  }
  
  useEffect(() =>
  {
    setData({ state: apiStates.LOADING, error: '', data: null });
    fetchData();
  }, [callProperties]); //check if this is the best solution. 
  return [{data}, setCallProperties];
}