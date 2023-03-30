import { Line } from "react-chartjs-2";
import { Chart as ChartJS, ArcElement, Tooltip, Legend } from "chart.js/auto";

ChartJS.register(ArcElement, Tooltip, Legend);

export const Graph = ({ data }) => {
    return (
        <Line
            data={data}
            options={{
                plugins: {
                    legend: {
                        display: false,
                    },
                },
            }}
        />
    );
};
