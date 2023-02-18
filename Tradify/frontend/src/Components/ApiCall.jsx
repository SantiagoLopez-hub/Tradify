import { useEffect, useState } from "react";
import axios from "axios";

const ApiCall = (request, endpoint, payload) => {
    const [data, setData] = useState([]);
    const [isLoading, setLoading] = useState(true);
    const [error, setError] = useState(false);

    useEffect(() => {
        const GetData = async () => {
            if (request === "POST") {
                return await axios
                    .post(process.env.REACT_APP_DOMAIN + endpoint, payload)
                    .then((response) => {
                        return response.data;
                    });
            }

            return await axios
                .get(process.env.REACT_APP_DOMAIN + endpoint, payload)
                .then((response) => {
                    return response.data;
                });
        };

        GetData()
            .then((res) => {
                setData(res);
                setLoading(false);
            })
            .catch((err) => {
                setError(err.message);
                setLoading(false);
            });
    }, [endpoint]);

    return [data, isLoading, error];
};

export default ApiCall;
