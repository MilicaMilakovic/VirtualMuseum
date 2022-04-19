import { HttpClient, JsonpClientBackend } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { Museum } from '../models/museum.model';
import { Tour } from '../models/tour.model';

@Injectable({
  providedIn: 'root',
})
export class MuseumService {
  url = 'http://localhost:9000/api/v1/museums';

  public museums: Museum[] = [];

  constructor(private http: HttpClient) {}

  logToConsole() {
    console.log('Museum service...');
  }

  public fetchData(): Observable<Museum[]> {
    // this.http.get<Museum[]>('http://localhost:9000/museums')
    //          .subscribe((data) => {
    //             console.log(data);
    //             console.log('-----');
    //             for(let i=0; i< data.length; i++){
    //                 console.log(data[i]);
    //                 this.museums.push(data[i]);
    //             }
    //             this.museums = data;

    //             console.log('Museums:');

    //             console.log( this.museums);

    //          });

    return this.http.get<Museum[]>(this.url, {withCredentials: true});
  }

  public getMuseumById(id: number) {
    return this.http.get<Museum>(this.url+'/'+id, {withCredentials: true});
  }

  public getToursInMuseum(id: number){
    return this.http.get<Tour[]>(this.url+'/'+id+'/tours', {withCredentials: true});
  }
}
