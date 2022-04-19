import { DOCUMENT } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, Inject, OnInit } from '@angular/core';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { gsap, Power1 } from 'gsap';
import { ScrollTrigger } from 'gsap/ScrollTrigger';
import { Tour } from 'src/app/models/tour.model';
import { SharedDataService } from 'src/app/services/shared-data.service';

gsap.registerPlugin(ScrollTrigger);

@Component({
  selector: 'app-tour',
  templateUrl: './tour.component.html',
  styleUrls: ['./tour.component.css'],
})
export class TourComponent implements OnInit {

  expired = false;

  photoURLs: string[] = [];
  tour!: Tour;
  museum : string | null = ''; 
  video! : SafeResourceUrl;

  notification = 'This tour has expired.';

  imageToShow: any;
  images: any[] = [];

  constructor(
    @Inject(DOCUMENT) private document: Document,
    private http: HttpClient,
    private sharedData: SharedDataService,
    private router: Router,
    private sanitizer: DomSanitizer
  ) {}

  ngOnInit(): void {

    this.tour = this.sharedData.tour;

    if(this.sharedData.tour === undefined)
      this.router.navigate(['/']);
    
    console.log(this.tour);
    this.museum = this.tour.museum.name;
    let url= this.tour.museum.presentation + '?autoplay=1&mute=1';
    this.video = this.sanitizer.bypassSecurityTrustResourceUrl(url);
    let tourDate = new Date(this.tour.start);

    this.titleAnimation();
    
    // if(tourDate.getTime() < new Date().getTime()) {
    //   this.expired = true;
    //   this.notification = 'This tour has expired.'
    // } else 
    if (tourDate.getTime() > new Date().getTime()) {
      this.expired = true;
      this.notification = 'This tour will start on ' + tourDate.toLocaleString('en-GB', {day:'numeric', month:'2-digit', year:'numeric'})+ ' at ' + tourDate.toLocaleString('en-GB', {hour:'2-digit', minute:'2-digit', hour12:false}) + " o'clock."; 
    }


    else if (new Date (tourDate.setHours(tourDate.getHours() + this.tour.duration)).getTime() < new Date().getTime())
      this.expired = true; 

   else setInterval( ()=> {
      if(new Date (tourDate.setHours(tourDate.getHours() + this.tour.duration)).getTime() <new Date().getTime())
        this.router.navigate(['/profile']);
    },1000);  

      console.log('expired ' + this.expired);
      
    // this.animations();

    this.http
      .get<string[]>('http://localhost:9000/api/v1/museums/presentation/' + this.tour.museum.name, {withCredentials: true})
      .subscribe((res) => {
        this.photoURLs = res;   
        console.log("FROM TOUR: " + res);             
      });

    setTimeout(() => this.animations(), 2000);
  }


  titleAnimation(){
    gsap.from(this.document.querySelector('.title'), {
      duration: 2.7,
      opacity: 0,
      scaleX: 1.2,
      scaleY: 1.2,
      y: -100,
      // scale: 1.1,
      stagger: 0,
      delay: 0.7,
    });
  }
  animations() {
    let masks = this.document.querySelectorAll('.mask');

    console.log('animation');
    
    masks.forEach((mask) => {
      let image = mask.querySelector('img');

      let tl = gsap.timeline({
        scrollTrigger: {
          trigger: mask,
          toggleActions: 'restart none none reset',
        },
      });

      tl.set(mask, { autoAlpha: 1 });

      tl.from(mask, 1.5, {
        xPercent: -100,
        ease: Power1.easeOut,
      });

      tl.from(image, 1.5, {
        xPercent: 100,
        scale: 1.3,
        delay: -1.5,
        ease: Power1.easeInOut,
      });
    });
  }
}
