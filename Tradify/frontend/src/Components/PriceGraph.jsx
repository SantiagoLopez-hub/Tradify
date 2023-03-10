import { CChart } from "@coreui/react-chartjs";
import { useEffect, useState } from "react";
import ApiCall from "./ApiCall";

const PriceGraph = ({ share_id }) => {
    let labels = [],
        data = [];
    const [graphData, setGraphData] = useState([]);

    const [tradingHistory] = ApiCall(
        "GET",
        `/shares/${share_id}/trading-history`,
        {
            headers: {
                Authorization: `Bearer ${localStorage.getItem("access_token")}`,
            },
        }
    );

    tradingHistory.map((item) => {
        labels.push(item.date);
        data.push(item.price);
        return;
    });

    useEffect(() => {
        let graph_Data = {
            labels: labels,
            datasets: [
                {
                    label: "Price",
                    data: data,
                    fill: false,
                    backgroundColor: "rgb(255, 99, 132)",
                    borderColor: "rgba(255, 99, 132, 0.2)",
                    tension: 0.15,
                },
            ],
        };

        setGraphData(graph_Data);
    }, [tradingHistory]);

    return (
        <div>
            <CChart type="line" data={graphData} />
        </div>
    );
};

export default PriceGraph;
