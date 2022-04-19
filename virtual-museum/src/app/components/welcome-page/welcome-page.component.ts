import {
  Component,
  ElementRef,
  Inject,
  OnInit,
  ViewChild,
} from '@angular/core';

import hoverEffect from 'hover-effect';

import { gsap } from 'gsap';
import { ScrollTrigger } from 'gsap/ScrollTrigger';
import { DOCUMENT } from '@angular/common';

gsap.registerPlugin(ScrollTrigger);

@Component({
  selector: 'app-welcome-page',
  templateUrl: './welcome-page.component.html',
  styleUrls: ['./welcome-page.component.css'],
})
export class WelcomePageComponent implements OnInit {
  @ViewChild('menu', { static: true }) menu:
    | ElementRef<HTMLDivElement>
    | undefined;
  @ViewChild('fifth', { static: true }) fifthSection:
    | ElementRef<HTMLDivElement>
    | undefined;
  @ViewChild('fifth_blur', { static: true }) fifthBlur:
    | ElementRef<HTMLDivElement>
    | undefined;
  @ViewChild('fifth_circle', { static: true }) fifth_circle:
    | ElementRef<HTMLDivElement>
    | undefined;
  @ViewChild('fifth_left', { static: true }) fifth_left:
    | ElementRef<HTMLDivElement>
    | undefined;
  @ViewChild('nike', { static: true }) nike:
    | ElementRef<HTMLDivElement>
    | undefined;
  @ViewChild('fifth', { static: true }) fifth_right:
    | ElementRef<HTMLDivElement>
    | undefined;

  constructor(@Inject(DOCUMENT) private document: Document) {}

  ngOnInit(): void {
    // var myAnimation = new hoverEffect({
    //   parent: document.querySelector('.third'),
    //   intensity: 0.3,
    //   image1: '../../../assets/elements/museum.png',
    //   image2: '../../../assets/elements/fourth-background.png',
    //   displacementImage: '../../assets/elements/heightMap.png'
    // });

    this.initialAnimations();
    this.initScrollAnimations();
  }

  initScrollAnimations(): void {
    gsap.to(this.document.querySelector('h1'), {
      scrollTrigger: {
        trigger: this.document.querySelector('h1'),
        scrub: true,
        start: '150% center',
      },
      color: '#fff',
      duration: 1.5,
      scale: 1.2,
      height: 380,
    });
    gsap.to(this.document.querySelector('.first-section-arrow'), {
      scrollTrigger: {
        trigger: this.document.querySelector('.first-section-arrow'),
        scrub: true,
        start: '150% center',
      },
      color: '#fff',
      duration: 1.7,
      scale: 1.2,
      height: 380,
    });

    gsap.to(this.document.querySelector('.first p'), {
      scrollTrigger: {
        trigger: this.document.querySelector('.first p'),
        scrub: true,
        start: '110% center',
      } as gsap.plugins.ScrollTriggerInstanceVars,
      duration: 1.1,
      scale: 1.2,
      height: 300,
    });
    gsap.to(this.document.querySelector('.more'), {
      scrollTrigger: {
        trigger: this.document.querySelector('.more'),
        scrub: true,
        start: 'top bottom-=100px ',
      } as gsap.plugins.ScrollTriggerInstanceVars,
      duration: 1.1,
      scale: 1.3,
      height: 300,
    });

    
   
    gsap.to(this.document.querySelector('.fourth h1'), {
      scrollTrigger: {
        trigger: this.document.querySelector('.fourth h1'),
        scrub: true,
        // markers: true,
        start: '-=250%',
        end: '-=50%'
      },
      duration: 2.5,
      scale: 1.1,
      height: 200,
      x: 70,
      y: 50,
    //  stagger:0.3
    });

    gsap.to(this.document.querySelector('.nike-of-samothrake'), {
      scrollTrigger: {
        trigger: this.document.querySelector('.nike-of-samothrake '),
        scrub: true,
        // markers: true,
        start: '-=500',
        end: '-=100'
      },
      duration: 1.1,
      scale: 1.15,
      stagger: 0.4
    });

    // gsap.to(this.document.querySelector('.fifth'), {
    //   scrollTrigger: {
    //     trigger: this.document.querySelector('.fifth-left p '),
    //     scrub: true,
    //     markers: true,
    //     start: '-=500',
    //     end: '-=100'
    //   },
    //   duration: 1.1,
    //   x: 20
    // });

  }

  initialAnimations(): void {
    gsap.from(this.document.querySelector('header'), {
      duration: 2.5,
      opacity: 0,
      y: -20,
      stagger: 0.1,
      delay: 0.75,
    });

    gsap.from(this.document.querySelector('h1'), {
      duration: 2,
      opacity: 0,
      x: -30,
      stagger: 0.2,
      delay: 1.5,
    });
    
    
    gsap.from(this.document.querySelector('.first-section-arrow'), {
      duration: 2,
      opacity: 0,
      x: -30,
      stagger: 0.2,
      delay: 1.5,
    });

    gsap.from(this.document.querySelector('.first p'), {
      duration: 2.7,
      opacity: 0,
      x: -30,
      stagger: 0.2,
      delay: 2.3,
    });

    gsap.from(this.document.querySelector('.first-section-circle'), {
      duration: 1.7,
      opacity: 0,
      x: -50,
      stagger: 0.02,
      delay: 2.5,
    });

    gsap.from(this.document.querySelector('.signature'), {
      duration: 1.8,
      opacity: 0,
      y: 200,
      stagger: 0.2,
      delay: 2.9,
    });
    
    
    gsap.from(this.document.querySelector('.scroll-btn'), {
      duration: 2.3,
      opacity: 0,
      y: -25,
      stagger: 0.2,
      delay: 2.5,
    });


  }
}
