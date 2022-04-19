import { HttpClient } from '@angular/common/http';
import { Component, HostBinding, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { NbComponentStatus, NbGlobalLogicalPosition, NbGlobalPhysicalPosition, NbGlobalPosition, NbIconConfig, NbToastrService } from '@nebular/theme';
import { User } from 'src/app/models/user.model';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {

  url = 'http://localhost:9000/api/v1/users/login';

  @HostBinding('class')
  classes = 'example-items-rows';

  physicalPositions = NbGlobalPhysicalPosition;
  logicalPositions = NbGlobalLogicalPosition;


  constructor(
    private router: Router,
    private http: HttpClient,
    private userService: UserService,
    private toastService: NbToastrService
  ) {}

  ngOnInit(): void {
    this.movement();
  }

  showPassword = false;

  logIn(form: NgForm) {
    console.log(form.value);
    let user = form.value;
    this.http.post<User>(this.url, user, {
      withCredentials: true
    }).subscribe(
      (res) => {
        console.log("login, user: " + res);

        if(res.role === "user" && res.approved == true){
          this.userService.user = res;
        
          this.router.navigate(['/museums']);
        } else if(res.approved == true){
          this.userService.admin = res;        
          this.router.navigate(['/admin/dashboard']);
        } else {
          this.showToast("Account hasn't been approved yet.");
        }
        
      },
      (err) => {
        console.log("FROM LOGIN:" +err);
        console.log("FROM LOGIN: " + user);       
        
        this.showToast('Try again!');  
      }
    );
  }


  showToast(message: string) {
    const iconConfig: NbIconConfig = { icon: 'alert-circle-outline', pack: 'eva' };
    this.toastService.show( message , "Login failed.",  iconConfig );
  }

  getInputType() {
    if (this.showPassword) {
      return 'text';
    }
    return 'password';
  }

  toggleShowPassword() {
    this.showPassword = !this.showPassword;
  }

  movement() {
    let card = document.querySelector('.card') as HTMLElement;
    const left = document.querySelector('.left');
    let img = document.querySelector('.david') as HTMLImageElement;

    left?.addEventListener('mousemove', (e) => {
      let event = e as MouseEvent;

      let xAxis = (window.innerWidth / 2 - event.pageX) / 20;
      let yAxis = (window.innerHeight / 2 - event.pageY) / 20;

      card.style.transform = `rotateY(${xAxis}deg) rotateX(${yAxis}deg)`;
    });

    left?.addEventListener('mouseenter', (e) => {
      card.style.transition = 'none';
      img.style.transform = 'translateZ(170px) rotateZ(-8deg)';
    });

    left?.addEventListener('mouseleave', (e) => {
      card.style.transition = 'all 0.5s ease';
      card.style.transform = `rotateY(0deg) rotateX(0deg)`;
      img.style.transform = 'translateZ(0px) rotateZ(0deg)';
    });
  }
}
