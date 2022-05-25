import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Temperature } from 'src/app/interfaces/temperature';
import { TemperatureService } from 'src/app/services/temperature.service';

@Component({
  selector: 'app-temperature',
  templateUrl: './temperature.component.html',
  styleUrls: ['./temperature.component.css']
})
export class TemperatureComponent implements OnInit {

  indoorTemp!: Temperature;
  outdoorTemp!: Temperature;

  tempSubscription: Subscription = new Subscription; 

  constructor(private temperatureService: TemperatureService) { 
    this.tempSubscription = this.temperatureService.getCurrentTemp().subscribe((value) => {
      this.indoorTemp = value
    })
  }

  ngOnInit(): void {
    
  }

}
