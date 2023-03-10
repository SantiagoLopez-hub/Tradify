import { CChart } from "@coreui/react-chartjs";
import { useEffect, useState } from "react";
import ApiCall from "./ApiCall";

const PriceGraph = ({ share_id }) => {
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

    useEffect(() => {
        let labels = [],
            data = [];

        tradingHistory.map((item) => {
            labels.push(item.date);
            data.push(item.price);
            return null;
        });
        let graph_Data = {
            labels: labels,
            datasets: [
                {
                    label: "Price",
                    data: data,
                    fill: false,
                    backgroundColor: "rgb(0, 0, 0, 0.5)",
                    borderColor: "rgba(0, 0, 0, 0.25)",
                    tension: 0.2,
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
