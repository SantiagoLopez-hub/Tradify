import { useEffect, useState } from "react";
import ApiError from "./ApiError";
import { getMyShares } from "./MyDetailsUtils";

const MyShares = ({ share_id }) => {
    const [sharesCount, setSharesCount] = useState(0);
    const [balance, setBalance] = useState(0);
    const [shares, isLoading, error] = getMyShares(share_id);

    useEffect(() => {
        setSharesCount(shares.quantity || 0);
        setBalance(shares?.user?.balance || 0);
    }, [shares]);

    return (
        <div className="border rounded py-3">
            {isLoading && <p>Loading...</p>}
            {error && <ApiError error={error} />}

            <span>My Shares: {sharesCount}</span>
            <br />
            <span>My Balance: {balance}</span>
        </div>
    );
};

export default MyShares;
