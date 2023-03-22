import AllMyShares from "../Components/AllMyShares";
import { getMyBalance } from "../Components/MyDetailsUtils";

const Portfolio = () => {
    const [myBalance] = getMyBalance();

    return (
        <div>
            <h1>Portfolio</h1>
            <h3>Balance: £{myBalance}</h3>
            <AllMyShares />
        </div>
    );
};

export default Portfolio;
