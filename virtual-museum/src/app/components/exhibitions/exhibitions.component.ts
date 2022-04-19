import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Tour } from 'src/app/models/tour.model';
import { TourService } from 'src/app/services/tour.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-exhibitions',
  templateUrl: './exhibitions.component.html',
  styleUrls: ['./exhibitions.component.css']
})
export class ExhibitionsComponent implements OnInit {

  public tours: Tour[] = [];
  constructor(private tourService: TourService, private router: Router, private userService: UserService) { }

  ngOnInit(): void {

    if(typeof this.userService.user === 'undefined')
        this.router.navigate(['']);

    this.tourService.fetchData().subscribe(res =>  {
      this.tours = res; 
      console.log(this.tours);
      
      this.tours = this.tours.filter(t =>  new Date(t.start).getTime() > new Date().getTime() )
      console.log(this.tours);
    },     
      err => {
        this.router.navigate(['/']);
      });
    
    
  }

}
