'use client'
import {
	CategoryScale,
	Chart as ChartJS,
	Legend,
	LinearScale,
	LineElement,
	PointElement,
	Title,
	Tooltip,
} from 'chart.js'
import annotationPlugin from 'chartjs-plugin-annotation'
import zoomPlugin from 'chartjs-plugin-zoom'
import { Line } from 'react-chartjs-2'

ChartJS.register(
	LineElement,
	PointElement,
	CategoryScale,
	LinearScale,
	Title,
	Tooltip,
	Legend,
	zoomPlugin,
	annotationPlugin
)

export default ({ xData, yData }) => {
	const data = {
		labels: xData,
		datasets: [
			{
				label: 'name of the function',
				data: yData,
				backgroundColor: '#4da6ff',
				borderColor: '#90c9f8',
				pointRadius: 4,
				pointHoverRadius: 10,
				hitRadius: 10,
				borderWidth: 2,
				pointBorderWidth: 0,
				// lineTension: 0.3,
			},
		],
	}

	const options = {
		responsive: true,
		maintainAspectRatio: false,

		scales: {
			x: {
				type: 'linear',
				beginAtZero: true,
				title: { display: true, text: 'X-values' },
				ticks: { stepSize: 1, font: { size: 16 } },
				max: Math.min(30, xData.length - 1),
				grid: {
					color: (ctx) => (ctx.tick.value == 0 ? 'black' : '#e5e5e5'),
					lineWidth: (ctx) => (ctx.tick.value == 0 ? 2 : 1),
				},
			},

			y: {
				type: 'linear',
				beginAtZero: true,
				title: { display: true, text: 'Y-values' },
				ticks: { stepSize: 1, font: { size: 16 } },
				max: Math.ceil((yData.reduce((a, b) => a + b) / yData.length) * 2),
				grid: {
					color: (ctx) => (ctx.tick.value == 0 ? 'black' : '#e5e5e5'),
					lineWidth: (ctx) => (ctx.tick.value == 0 ? 2 : 1),
				},
			},
		},

		plugins: {
			legend: { display: true },
			tooltip: {
				enabled: true,
				displayColors: false,
				callbacks: {
					title: (tooltipItem) => 'X-value: ' + tooltipItem[0].label,
					label: (tooltipItem) => 'Y-value: ' + tooltipItem.raw,
				},
			},

			zoom: {
				pan: { enabled: true, mode: 'xy', threshold: 0 },
				zoom: {
					enabled: true,
					mode: 'xy',
					speed: 0.1,
					sensitivity: 1,
					wheel: { enabled: true, speed: 0.1 },
					pinch: { enabled: true, speed: 0.1 },
				},
			},
		},
	}

	return <Line data={data} options={options} />
}
