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

export const useFetch = (initialUrl, initialOptions) =>
{
  const [data, setData] = useState({
    httpStatus: null,
    state: apiStates.LOADING,
    error: '',
    data: null,
  });
  
  const [callProperties, setCallProperties] = useState({ url: initialUrl, options: initialOptions })
  
  const fetchData = async () =>
  {
    if (callProperties.url !== null && callProperties.url !== undefined)
    {
      await fetch(callProperties.url, callProperties.options)
        .then((response) =>
        {
           setData((prevState) =>
            ({
              ...prevState, httpStatus: response.status
            }))
          return response.json();
        })
    .then((data) =>
    {
       setData((prevState) =>
            ({
         ...prevState,
        state: apiStates.SUCCESS,
        error: '',
        data: Object.keys(data)[0] === "content" ? data.content : data
            }))
    }
    )
    .catch((e) =>
    {
      setData((prevState) =>
            ({
         ...prevState,
        state: apiStates.ERROR,
        error: e.message,
        data: null
            }))
    });
      }   
  }
  
  useEffect(() =>
  {
    let ignore = false
    if (!ignore) fetchData();
    return () =>
    {
      ignore = true
    };
  }, [callProperties]); //check if this is the best solution. 
  
  return [{data}, setCallProperties];
}