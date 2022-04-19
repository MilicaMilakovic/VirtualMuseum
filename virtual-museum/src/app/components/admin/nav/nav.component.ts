import { HttpClient, HttpRequest } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';


@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent implements OnInit {

  constructor(private http: HttpClient, private router: Router) { }

  ngOnInit(): void {
    
  }

  logOut(){
    this.http.get('http://localhost:9000/api/v1/users/logout', {withCredentials: true}).subscribe();
    this.router.navigate(['/']);
  }
}
