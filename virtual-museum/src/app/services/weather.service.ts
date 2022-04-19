import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Weather } from '../models/weather.model';

@Injectable({
  providedIn: 'root',
})
export class WeatherService {

  private CITIES_URL: string =
    'https://countriesnow.space/api/v0.1/countries/cities';
  private WEATHER_URL: string = `https://api.openweathermap.org/data/2.5/weather?q={$city}&units=metric&appid=fc5d94e9f1ada58e4cc9759fed91e3dd`;

  public country: string | null = 'France';
  public result: Array<Weather> = [];

  public cities: string[] | undefined = [];

  constructor(private http: HttpClient) {
      
  }

  async getWeather() {
    this.result = [];
    // console.log('Country: ' + this.country);

    await this.http
      .post<{ data: Array<string> }>(this.CITIES_URL, { country: this.country })
      .toPromise()
      .then((responseData) => {
        let shuffled = responseData?.data.sort(() => 0.5 - Math.random());
        this.cities = shuffled?.slice(0, 6);
        // console.log(this.cities);
      });
      

    // console.log('Cities : ' + this.cities);

    this.cities?.forEach((city) => {
      let url =
        'https://api.openweathermap.org/data/2.5/weather?q=' +
        city +
        '&units=metric&appid=fc5d94e9f1ada58e4cc9759fed91e3dd';

      this.http
        .get<{ main: { temp: string } }>(url)
        .subscribe((responseData) => {
          this.result.push(new Weather(city, responseData?.main.temp)          
          );
          
        },
        (error) => {
            console.log('Temp for the city not found');
        });
    });
  
    return this.result;
  }
}
