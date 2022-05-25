import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Temperature } from '../interfaces/temperature';

@Injectable({
  providedIn: 'root'
})
export class TemperatureService {

  private url: string = "http://192.168.1.158:8080/temp"

  constructor(private httpClient: HttpClient) { }

  getCurrentTemp(): Observable<Temperature> {
    return this.httpClient.get<Temperature>(this.url);
  }

  getOutdoorTemp(): Observable<Temperature> {
    return this.httpClient.get<Temperature>(this.url + "/outdoor")
  }
}
