import {
  HttpClient,
  HttpErrorResponse,
  HttpResponse,
} from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import {
  NbGlobalPhysicalPosition,
  NbIconConfig,
  NbToastrService,
} from '@nebular/theme';
import { Ticket } from 'src/app/models/ticket.model';
import { Tour } from 'src/app/models/tour.model';
import { TourService } from 'src/app/services/tour.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css'],
})
export class CheckoutComponent implements OnInit {
  url = 'http://localhost:9000/api/v1/bank';

  cardholder = '';
  cardNumber = '';
  exp = '';
  type = 'visa';
  pin = '';

  tour!: Tour;
  tourId!: number;
  museumName: string | null = '';

  time = '';

  constructor(
    private route: ActivatedRoute,
    private tourService: TourService,
    private userService: UserService,
    private router: Router,
    private toastService: NbToastrService,
    private http: HttpClient
  ) {}

  ngOnInit(): void {
    if (typeof this.userService.user === 'undefined') this.router.navigate(['/']);

    this.route.params.subscribe((params) => {
      console.log(params['tourId']);
      this.tourId = params['tourId'];
    }, err =>  this.router.navigate(['/']));

       
    this.tourService.getTourById(this.tourId).subscribe((res) => {
      this.tour = res;
      console.log(this.tour);
      this.time= new Date(this.tour.start).toLocaleString('en-GB',{year: 'numeric', month: 'numeric', day: 'numeric', hour: '2-digit', minute: '2-digit' });
      this.tour.start = this.time;
      this.museumName = this.tour.museum.name;
    });
  }

  buyTicket() {
    console.log('buying ticket...');

    console.log(this.userService.user.id + ' tour id: ' + this.tour.id);

    const paymentDetails = {
      userId: this.userService.user.id,
      tourId: this.tour.id,
      cardholder: this.cardholder,
      cardNumber: this.cardNumber.replace(' ', ''),
      expDate: this.exp,
      type: this.type,
      pin: this.pin,
    };

    console.log("GET -->");
    

    // this.http.get(this.url, {withCredentials: true}).subscribe(res => console.log(res));
    
    console.log("POST -->");

    this.http
      .post<any>(this.url, paymentDetails, {
        withCredentials: true
      })
      .subscribe(
        (res) => {
          console.log(res);

          if (res.status === 400)
            this.showToast('' + res.body, 'Payment failed.', 'error');
          else if (res.status === 500) {
            this.showToast('Try again later.', 'Error', 'error');
          } else {
            this.showToast(
              'Find the ticket on your profile and start the virtual tour now!',
              'You have bought the ticket!',
              'success'
            );       
            this.showToast(
              'The ticket should arrive soon.',
              'Check your email!',
              'basic'
            );       
            this.userService.refresh();
          }
        },
        (err: HttpErrorResponse) => {
          // console.log(err.error);
          this.showToast('' + err.error, 'Payment failed.', 'danger');
        }
      );

    console.log(paymentDetails);
  }

  showToast(message: string, title: string, status: string) {
    let icon = "email-outline";

    if (status === 'danger'){
      icon = 'alert-circle-outline'
    } else if (status === 'success'){
      icon =  'checkmark-circle-outline';
    }
  
    const iconConfig: NbIconConfig = {
      icon: icon,
      pack: 'eva',
    };
    this.toastService.show(message, title, {
      duration: 6000,
      destroyByClick: true,
      hasIcon: true,
      icon: iconConfig,
      status: status,
      position: NbGlobalPhysicalPosition.BOTTOM_RIGHT,
    });
  }
}
