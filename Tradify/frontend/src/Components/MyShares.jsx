import { useEffect, useState } from "react";
import ApiCall from "./ApiCall";
import ApiError from "./ApiError";

const MyShares = ({ share_id }) => {
    const [sharesCount, setSharesCount] = useState(0);

    const [shares, isLoading, error] = ApiCall(
        "GET",
        `/users/${localStorage.getItem("username")}/${share_id}`,
        {
            headers: {
                Authorization: `Bearer ${localStorage.getItem("access_token")}`,
            },
        }
    );

    useEffect(() => {
        setSharesCount(shares.length);
    }, [shares]);

    return (
        <div className="border rounded py-3">
            {isLoading && <p>Loading...</p>}
            {error && <ApiError error={error} />}

            <span>My Shares: {sharesCount}</span>
        </div>
    );
};

export default MyShares;
