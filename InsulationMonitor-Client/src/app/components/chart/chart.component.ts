import { Component, OnInit } from '@angular/core';
import { Chart, ChartItem } from 'chart.js';

@Component({
  selector: 'app-chart',
  templateUrl: './chart.component.html',
  styleUrls: ['./chart.component.css']
})
export class ChartComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
    let ctx = document.getElementById("myChart")
    let myChart = new Chart(ctx as ChartItem, {})
  }

}
