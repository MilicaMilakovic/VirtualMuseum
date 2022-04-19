import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Tour } from '../models/tour.model';

@Injectable({
  providedIn: 'root',
})
export class TourService {

    url: string = "http://localhost:9000/api/v1/tours";

    public tours: Tour[] = [];
    
    constructor(private http: HttpClient){

    }

    public fetchData(): Observable<Tour[]>{
        return this.http.get<Tour[]>(this.url,{withCredentials: true});
    }

    public getTourById(id: number): Observable<Tour>{
      return this.http.get<Tour>(this.url+'/'+id,{withCredentials: true});
    }

}
