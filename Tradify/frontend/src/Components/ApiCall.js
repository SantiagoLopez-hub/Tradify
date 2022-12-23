import {useEffect, useState} from 'react';
import axios from "axios";

const ApiCall = (endpoint) => {
    const [data, setData] = useState([]);
    const [isLoading, setLoading] = useState(true);

    useEffect(() => {
        const GetData = async () => {
            return await axios.get(process.env.REACT_APP_DOMAIN + endpoint)
                .then(response => {
                    return response.data;
                })
                .catch(error => console.log(error));
        }

        GetData().then(res => {
            setData(res);
            setLoading(false);
        });
    }, [endpoint]);

    return [data, isLoading];
}

export default ApiCall;
