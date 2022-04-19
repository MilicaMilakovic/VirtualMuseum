import { Component,  Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Museum } from 'src/app/models/museum.model';
import { SharedDataService } from 'src/app/services/shared-data.service';

@Component({
  selector: 'app-museum-card',
  templateUrl: './museum-card.component.html',
  styleUrls: ['./museum-card.component.css']
})
export class MuseumCardComponent implements OnInit {

  @Input('museum')public museum!: Museum;
  public location: string = "";
  public image: string | null = "";
  constructor(private sharedDataService: SharedDataService, private router: Router) { }

  ngOnInit(): void {
    this.location= this.museum.city + ", " + this.museum.country;  
    this.image = this.museum.image;
  }

  goToDetails(){
    this.sharedDataService.museum = this.museum;
    this.router.navigate(['/museums/details']);
  }
}
