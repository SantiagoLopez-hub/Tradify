import { useEffect, useState } from "react";
import DoughnutChart from "../Components/DoughnutChart";
import { getAllMyShares } from "../Components/MyDetailsUtils";

const AllMyShares = () => {
    const [shares, setShares] = useState([]);
    const [myShares] = getAllMyShares();

    useEffect(() => {
        setShares(myShares);
    }, [myShares]);

    let labels = [];
    let quantity = [];
    let colours = [];

    for (let i = 0; i < shares.length; i++) {
        labels.push(shares[i].share.name);
        quantity.push(shares[i].quantity);
        colours.push(shares[i].share.colour);
    }

    const data = {
        labels: labels,
        datasets: [
            {
                label: "My shares",
                data: quantity,
                backgroundColor: colours,
            },
        ],
    };

    return <DoughnutChart data={data} />;
};

export default AllMyShares;
