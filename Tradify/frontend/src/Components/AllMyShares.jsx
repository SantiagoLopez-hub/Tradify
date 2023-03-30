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

    // The following code is from user "jay" on Stack Overflow":
    // https://stackoverflow.com/questions/53068562/add-text-inside-the-doughnut-chart-of-the-react-chartjs-2-box-to-react
    // Posted on 25th May 2021
    const plugins = [
        {
            beforeDraw: (chart) => {
                let width = chart.width,
                    height = chart.height,
                    ctx = chart.ctx;
                ctx.restore();
                let fontSize = (height / 360).toFixed(2);
                ctx.font = fontSize + "em sans-serif";
                ctx.textBaseline = "top";
                let text = "Total value: £5,140,703",
                    textX = Math.round(
                        (width - ctx.measureText(text).width) / 2
                    ),
                    textY = height / 2.1;
                ctx.fillText(text, textX, textY);
                ctx.save();
            },
        },
    ];

    return <DoughnutChart data={data} plugins={plugins} />;
};

export default AllMyShares;
