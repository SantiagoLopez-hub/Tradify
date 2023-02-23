import axios from "axios";

const RefreshToken = async () => {
    await axios
        .get(process.env.REACT_APP_DOMAIN + "/jwt/refresh", {
            headers: {
                Authorization: `Bearer ${localStorage.getItem(
                    "refresh_token"
                )}`,
            },
        })
        .then(async (response) => {
            await localStorage.setItem(
                "access_token",
                response?.data?.access_token
            );
            await localStorage.setItem(
                "refresh_token",
                response?.data?.refresh_token
            );
        })
        .catch((err) => {
            console.log(err.message);
        });
};

export default RefreshToken;
