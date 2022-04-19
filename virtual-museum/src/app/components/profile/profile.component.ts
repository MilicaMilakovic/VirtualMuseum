import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user.model';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  user!: User;
  
  constructor(private userService: UserService, private router: Router, private http: HttpClient) { }

  ngOnInit(): void {

    // console.log(this.userService.user);
    
    if( typeof this.userService.user === 'undefined')
      this.router.navigate(['']);

    this.user = this.userService.user;    
   

  }

  logOut(){
    this.http.get('http://localhost:9000/api/v1/users/logout', {withCredentials: true}).subscribe();
    this.router.navigate(['/']);
  }

}
