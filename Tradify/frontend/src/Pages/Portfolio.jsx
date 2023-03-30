import AllMyShares from "../Components/AllMyShares";
import { getMyBalance } from "../Components/MyDetailsUtils";

const Portfolio = () => {
    const [myBalance] = getMyBalance();

    return (
        <div>
            <h1>Portfolio</h1>
            <h3>Balance: £{myBalance.toLocaleString("en-US")}</h3>

            <div className="w-50 center">
                <AllMyShares />
            </div>
        </div>
    );
};

export default Portfolio;
