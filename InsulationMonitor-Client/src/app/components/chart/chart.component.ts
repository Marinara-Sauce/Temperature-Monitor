import { Component, OnInit } from '@angular/core';
import { Chart, ChartData, registerables } from 'chart.js';

@Component({
  selector: 'app-chart',
  templateUrl: './chart.component.html',
  styleUrls: ['./chart.component.css']
})
export class ChartComponent implements OnInit {

  chart: any = [];

  private data: ChartData = {
    datasets: [
      {
        label: 'Dataset 1',
        data: [{x: 1, y: 2}, {x: 50, y: 2}, {x: 200, y: 2}],
        borderColor: 'rgb(255, 0, 0)',
        backgroundColor: 'rgba(255, 0, 0, 0.5)',
        yAxisID: 'y'
      },
      {
        label: 'Dataset 2',
        data: [{x: 1, y: 2}, {x: 5, y: 2}, {x: 10, y: 2}],
        borderColor: 'rgb(0, 0, 255)',
        backgroundColor: 'rgba(0, 0, 255, 0.5)',
        yAxisID: 'y1'
      }
    ]
  }

  constructor() { 
    Chart.register(...registerables)
  }

  ngOnInit(): void {
    this.chart = new Chart('myChart', {
      type: 'line',
      data: this.data,
      options: {
        responsive: true,
        maintainAspectRatio: false,
        interaction: {
          mode: 'index',
          intersect: false
        },
        scales: {
            y: {
              type: 'linear',
              display: true,
              position: 'left'
            },
            y1: {
              type: 'linear',
              display: true,
              position: 'right',

              grid: {
                drawOnChartArea: false,
              }
            }
        }
      }
    });
  }

}
