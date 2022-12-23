import {useEffect, useState} from 'react';
import axios from "axios";

const ApiCall = (endpoint) => {
    const [data, setData] = useState([]);
    const [isLoading, setLoading] = useState(true);
    const [error, setError] = useState(false);

    useEffect(() => {
        const GetData = async () => {
            return await axios.get(process.env.REACT_APP_DOMAIN + endpoint)
                .then(response => {
                    return response.data;
                });
        }

        GetData().then(res => {
            setData(res);
            setLoading(false);
        }).catch(err => {
            setError(err.message);
            setLoading(false);
        });
    }, [endpoint]);

    return [data, isLoading, error];
}

export default ApiCall;
