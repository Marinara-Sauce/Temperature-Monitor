import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Temperature } from '../interfaces/temperature';

@Injectable({
  providedIn: 'root'
})
export class TemperatureService {

  private url: string = environment.apiUrl + "/temp"

  constructor(private httpClient: HttpClient) { }

  getCurrentTemp(): Observable<Temperature> {
    return this.httpClient.get<Temperature>(this.url);
  }

  getOutdoorTemp(): Observable<Temperature> {
    return this.httpClient.get<Temperature>(this.url + "/outdoor")
  }
}
