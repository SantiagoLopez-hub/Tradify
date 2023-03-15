import { useEffect, useState } from "react";
import { Graph } from "./Graph";
import { getTradingHistory } from "./SystemUtils";

const StockGraph = ({ share_id }) => {
    const [graphData, setGraphData] = useState([]);
    const [tradingHistory] = getTradingHistory(share_id);

    useEffect(() => {
        let labels = [],
            data = [];

        tradingHistory.map((item) => {
            labels.push(item.date.split("T")[0]);
            data.push(item.price);
            return null;
        });

        let graph_Data = {
            labels: labels,
            datasets: [
                {
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

    return graphData?.labels?.length > 0 ? (
        <Graph data={graphData} />
    ) : (
        <p>No graph data available</p>
    );
};

export default StockGraph;
