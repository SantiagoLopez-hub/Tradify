import { CChart } from "@coreui/react-chartjs";
import { useEffect, useState } from "react";

const PriceGraph = () => {
    const [graphData, setGraphData] = useState([]);

    useEffect(() => {
        let graph_Data = {
            labels: [
                "January",
                "February",
                "March",
                "April",
                "May",
                "June",
                "July",
            ],
            datasets: [
                {
                    label: "Price",
                    data: [65, 59, 80, 81, 56, 55, 40],
                    fill: false,
                    backgroundColor: "rgb(255, 99, 132)",
                    borderColor: "rgba(255, 99, 132, 0.2)",
                    tension: 0.15,
                },
            ],
        };

        setGraphData(graph_Data);
    }, []);

    return (
        <div>
            <CChart type="line" data={graphData} />
        </div>
    );
};

export default PriceGraph;
