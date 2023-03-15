import { useEffect, useState } from "react";
import ApiError from "./ApiError";
import { getMyShares } from "./MyDetailsUtils";

const MyShares = ({ share_id }) => {
    const [sharesCount, setSharesCount] = useState(0);
    const [shares, isLoading, error] = getMyShares(share_id);

    useEffect(() => {
        setSharesCount(shares.quantity || 0);
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
