import { useEffect, useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";
import RefreshToken from "../Auth/RefreshToken";

const ApiCall = (request, endpoint, payload) => {
    const [data, setData] = useState([]);
    const [isLoading, setLoading] = useState(true);
    const [error, setError] = useState(false);
    let counter = 0;

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
            .catch(async (err) => {
                setError(err.message);
                setLoading(false);
                counter += 1;

                await console.log(counter);

                if (err.response.status === 403 && counter === 1) {
                    await RefreshToken();
                    if (localStorage.getItem("logged_in") === "true") {
                        localStorage.setItem("logged_in", "false");
                        window.location.reload();
                    }
                } else if (err.response.status === 403 && counter > 1) {
                    window.location.href = "/login";
                }
            });
    }, [endpoint]);

    return [data, isLoading, error];
};

export default ApiCall;
