import AllMyShares from "../Components/AllMyShares";
import ApiError from "../Components/ApiError";
import { getMyBalance } from "../Components/MyDetailsUtils";

const Portfolio = () => {
    const [myBalance, isLoading, error] = getMyBalance();

    return (
        <div>
            {error && <ApiError error={error} />}

            {isLoading ? (
                <p>Loading...</p>
            ) : (
                <>
                    <h1>Portfolio</h1>
                    <h3>Balance: £{myBalance.toLocaleString("en-US")}</h3>

                    <div className="w-50 center">
                        <AllMyShares />
                    </div>
                </>
            )}
        </div>
    );
};

export default Portfolio;
