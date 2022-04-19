import { Component, Input, OnInit } from '@angular/core';
import { Tour } from 'src/app/models/tour.model';
import { MuseumService } from 'src/app/services/museum.service';
import { TourService } from 'src/app/services/tour.service';

@Component({
  selector: 'app-tour-card',
  templateUrl: './tour-card.component.html',
  styleUrls: ['./tour-card.component.css'],
})
export class TourCardComponent implements OnInit {

  @Input('tour') tour!: Tour;
  time = '';


  constructor(private tourService: TourService, private museumService: MuseumService) {}

  ngOnInit(): void {
    this.time= new Date(this.tour.start).toLocaleString('en-GB',{year: 'numeric', month: 'numeric', day: 'numeric', hour: '2-digit', minute: '2-digit' });
    this.tour.start = this.time;
    // console.log(new Date(this.tour.start).toLocaleString('en-GB',{year: 'numeric', month: 'numeric', day: 'numeric', hour: '2-digit', minute: '2-digit' }));
    
  }
}
