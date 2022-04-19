import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Museum } from 'src/app/models/museum.model';
import { MuseumService } from 'src/app/services/museum.service';
@Component({
  selector: 'app-tour-form',
  templateUrl: './tour-form.component.html',
  styleUrls: ['./tour-form.component.css']
})
export class TourFormComponent implements OnInit {

  museums: Museum[] = [];
  photos:File[]= [];
  selectedMuseum = "";
  

  constructor(private museumService: MuseumService, private http: HttpClient) { }

  ngOnInit(): void {
    
    this.museumService.fetchData().subscribe(res => this.museums = res);

  }

  onPhotosSelected(event: any){
   this.photos = event.target.files;
  }

  submitForm(form: NgForm){
    let museum = this.museums.find(e => e.name === this.selectedMuseum);
    let tour = {
      ...form.value,
      museum: museum
    }

    console.log(tour);

    this.http.post('http://localhost:9000/api/v1/tours', tour, {withCredentials: true})
             .subscribe(res => {               
               console.log(res)});
    
             
  }

}
