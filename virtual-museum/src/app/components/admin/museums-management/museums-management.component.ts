import { Component, OnInit, ViewChild, TemplateRef } from '@angular/core';
import { Museum } from 'src/app/models/museum.model';
import { MuseumService } from 'src/app/services/museum.service';
import { NbWindowService } from '@nebular/theme';
import { MuseumFormComponent } from './museum-form/museum-form.component';
import { TourFormComponent } from './tour-form/tour-form.component';
import { User } from 'src/app/models/user.model';
import { UserService } from 'src/app/services/user.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-museums-management',
  templateUrl: './museums-management.component.html',
  styleUrls: ['./museums-management.component.css'],
})
export class MuseumsManagementComponent implements OnInit {
  public museums: Museum[] = [];
  private admin!: User;
  @ViewChild('contentTemplate') contentTemplate!: TemplateRef<any>;


  constructor(private museumService: MuseumService, private windowService: NbWindowService, private userService: UserService, private router: Router) {}

  ngOnInit(): void {
    this.admin = this.userService.admin;

    if(typeof this.admin === 'undefined')
      this.router.navigate(['/login']);  

    this.museumService.fetchData().subscribe((data) => {
      this.museums = data;
    });
  }

  
  openWindow() {
    // this.windowService.open(
    //   this.contentTemplate,
    //   { title: 'Add new museum', context: { text: '' } },
    // );

    const windowRef = this.windowService.open(MuseumFormComponent, { title: `Add new museum` });
    

  }

  addTour(){
    const windowRef = this.windowService.open(TourFormComponent, { title: `Add new tour` });

  }
}
