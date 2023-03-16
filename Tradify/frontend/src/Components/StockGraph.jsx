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

        setGraphData({
            labels: labels,
            datasets: [
                {
                    data: data,
                    fill: false,
                    backgroundColor: "#FFF",
                    borderColor:
                        data[0] < data[data.length - 1] ? "#00AB00" : "#FF0000",
                    tension: 0.25,
                },
            ],
        });
    }, [tradingHistory]);

    return graphData?.labels?.length > 0 ? (
        <Graph data={graphData} />
    ) : (
        <p>No graph data available</p>
    );
};

export default StockGraph;
