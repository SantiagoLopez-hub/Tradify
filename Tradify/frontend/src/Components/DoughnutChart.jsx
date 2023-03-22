import React from "react";
import { Chart as ChartJS, ArcElement, Tooltip, Legend } from "chart.js/auto";
import { Doughnut } from "react-chartjs-2";

ChartJS.register(ArcElement, Tooltip, Legend);

const DoughnutChart = ({ data, plugins }) => {
    return <Doughnut data={data} plugins={plugins} />;
};

export default DoughnutChart;
