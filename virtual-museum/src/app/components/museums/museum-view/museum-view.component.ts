import { DOCUMENT } from '@angular/common';
import { Component, Inject, OnInit } from '@angular/core';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { ActivatedRoute, Router } from '@angular/router';
import { gsap } from 'gsap';
import { Museum } from 'src/app/models/museum.model';
import { Tour } from 'src/app/models/tour.model';
import { Weather } from 'src/app/models/weather.model';
import { MuseumService } from 'src/app/services/museum.service';
import { SharedDataService } from 'src/app/services/shared-data.service';
import { WeatherService } from 'src/app/services/weather.service';

@Component({
  selector: 'app-museum-view',
  templateUrl: './museum-view.component.html',
  styleUrls: ['./museum-view.component.css'],
})
export class MuseumViewComponent implements OnInit {
  museum!: Museum;
  tours: Tour[] = [];

  weather: Array<Weather> = [new Weather('',''),new Weather('',''),new Weather('','')];
  // map!: string;
  map!: SafeResourceUrl;
  url: string ="";

  constructor(
    @Inject(DOCUMENT) private document: Document,
    private route: ActivatedRoute,
    private sharedDataService: SharedDataService,
    private router: Router,
    private weatherService: WeatherService,
    private sanitizer: DomSanitizer,
    private museumService: MuseumService
  ) {}

  ngOnInit(): void {
    this.initialAnimations();

    if (this.sharedDataService.museum == null) {
      this.router.navigate(['/museums']);
    }

    console.log(this.weather);
    this.museum = this.sharedDataService.museum;
    this.museumService.getToursInMuseum(this.museum?.id)
    .subscribe(res => {
      this.tours = res;
      this.tours = this.tours.filter(t =>  new Date(t.start).getTime() > new Date().getTime() );
    });

    
    // window.addEventListener("scroll", function(){
    //   let offset=this.window.pageYOffset;
    //   let parallax = this.document.querySelector('.info') as HTMLDivElement;
    //   parallax.style.backgroundPositionY = offset*0.7 + "px";
    // });

    
    this.weatherService.country = this.museum.country;
    this.weatherService.getWeather()
    this.weather = this.weatherService.result;

    console.log('-----------');
    
    console.log(typeof this.weather[0]);
    console.log(this.weather);
    
    // this.map ="https://maps.google.com/maps?hl=en&amp;q="+ this.museum.latitude+"," + this.museum.longitude+"+(Virtual%20Museum)&amp;t=&amp;z=14&amp;ie=UTF8&amp;iwloc=B&amp;output=embed";

    // this.map= "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2436.574562788909!2d2.334595!3d48.864824!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0%3A0x57972e81cfe250ee!2zNTLCsDIxJzM2LjAiTiA0wrA1MycwNy4wIkU!5e0!3m2!1sen!2sba!4v1648221055611!5m2!1sen!2sba";
    // this.url="https://maps.google.com/maps/embed?ll="+this.museum.longitude+","+this.museum.latitude+"&spn=0.004250,0.011579&t=h&iwloc=A&hl=en";

    this.url = 'https://maps.google.com/maps?q=' + this.museum.latitude + ',' + this.museum.longitude + '&t=&z=15&ie=UTF8&iwloc=&output=embed'
    // this.url = "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2436.574562788909!2d"+this.museum.longitude+"!3d"+this.museum.latitude+"!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0%3A0x57972e81cfe250ee!2zNTLCsDIxJzM2LjAiTiA0wrA1MycwNy4wIkU!5e0!3m2!1sen!2sba!4v1648222115981!5m2!1sen!2sba";

    console.log(this.url);
    
    this.map= this.sanitizer.bypassSecurityTrustResourceUrl(this.url);
    console.log(this.map);

   


    
  }

  
  initialAnimations(): void {
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

}
