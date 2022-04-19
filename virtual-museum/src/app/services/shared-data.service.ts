import { Injectable } from "@angular/core";
import { Museum } from "../models/museum.model";
import { Tour } from "../models/tour.model";

@Injectable({
    providedIn: 'root'
})
export class SharedDataService{
    museum!: Museum;

    tour!: Tour;
}