import React, { useState, useEffect} from 'react'

const useFetch = (url) => {
    const [data, setData] = useState([{}]);

    useEffect(() => {
        if (!url) return;
        const fetchData = async () => {
          const response = await fetch(url);
          const data = await response.json();
          setData(data.content);
        };

        fetchData();
    }, [url]);

    return data;
};
export default useFetch;