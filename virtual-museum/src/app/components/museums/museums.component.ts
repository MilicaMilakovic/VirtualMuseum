import { Component, OnInit } from '@angular/core';
import { Museum } from 'src/app/models/museum.model';
import { MuseumService } from 'src/app/services/museum.service';
import { NbSearchService } from '@nebular/theme';
import { NbTagComponent } from '@nebular/theme';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-museums',
  templateUrl: './museums.component.html',
  styleUrls: ['./museums.component.css'],
  providers: [ MuseumService ]
})
export class MuseumsComponent implements OnInit {

  public museums: Museum[] = [];
  key = '';

  constructor(private museumService: MuseumService, private searchService: NbSearchService, 
              private router: Router, private userService: UserService) { }

  ngOnInit(): void {
     
    if(typeof this.userService.user === 'undefined')
        this.router.navigate(['']);

      this.getMuseums();
      this.searchService.onSearchActivate()
                        .subscribe(()=>{
                          this.getMuseums();
                        });

      this.searchService.onSearchSubmit()
                        .subscribe((data: any)=> {
                          this.key = data.term;
                          console.log(this.key);  
                          this.searchMuseums(this.key);                        
                        });
      
    
  }

  getMuseums(){
    this.museumService.fetchData()
    .subscribe(
      data => {
        this.museums = data;      
        
      },
      err => {
        this.router.navigate(['/']);
      }
    );
  }

  searchMuseums(searchKey: string){
    this.museums= this.museums.filter(museum => museum.name?.toLowerCase().includes(searchKey.toLowerCase()) || museum.city?.toLowerCase().includes(searchKey.toLowerCase()));
    console.log(this.museums);
    
  }

}
