import { useEffect, useState } from "react";
import { getAllMyShares } from "../Components/MyDetailsUtils";

const Portfolio = () => {
    const [shares, setShares] = useState([]);
    const [myShares] = getAllMyShares();

    useEffect(() => {
        setShares(myShares);
    }, [myShares]);

    console.log(shares);

    return (
        <div>
            <h1>Portfolio</h1>
        </div>
    );
};

export default Portfolio;
