import { HttpClient } from '@angular/common/http';
import { Component, HostBinding, OnInit } from '@angular/core';
import { AbstractControl, NgForm, ValidationErrors, ValidatorFn } from '@angular/forms';
import { NbGlobalLogicalPosition, NbGlobalPhysicalPosition, NbGlobalPosition, NbToastRef, NbToastrService } from '@nebular/theme';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  url: string = "http://localhost:9000/api/v1/users/register";

  showPassword = true;
  password= '';
  confirmPassword='';

  @HostBinding('class')
  classes = 'example-items-rows';

  physicalPositions = NbGlobalPhysicalPosition;
  logicalPositions = NbGlobalLogicalPosition;

  constructor(private http: HttpClient, private toastService: NbToastrService) { }

  ngOnInit(): void {
      this.movement();

  }
 
  register(form: NgForm){
    console.log(form.value);
    const user = form.value;

    // console.log(this.physicalPositions);
    delete user.confirmPassword;
    console.log(user);
    
    this.http.post(this.url, user)
             .subscribe(res => {
               console.log(res);  
               this.showToast("Registration successful.",'Success!',this.physicalPositions.BOTTOM_RIGHT);        
               form.reset();   
             }, err => {
               console.log(err);     
               this.showToast("Registration failed.","Failure",this.physicalPositions.BOTTOM_RIGHT);            
             });  
  }
 

  showToast(message: string, title: string, position: NbGlobalPosition) {
    this.toastService.show(message, title, { position });
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
      
      let xAxis = (window.innerWidth / 2 - event.pageX) /20;
      let yAxis = (window.innerHeight / 2 - event.pageY) / 20;

      card.style.transform = `rotateY(${xAxis}deg) rotateX(${yAxis}deg)`
      
    });

    
    left?.addEventListener('mouseenter', (e) => {
      card.style.transition = 'none';
      img.style.transform = "translateZ(170px) rotateZ(-8deg)";
    });

    left?.addEventListener('mouseleave', (e) => {
      card.style.transition = 'all 0.5s ease';
      card.style.transform = `rotateY(0deg) rotateX(0deg)`;
      img.style.transform = "translateZ(0px) rotateZ(0deg)";

    });
  }


 
}
