import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-museum-form',
  templateUrl: './museum-form.component.html',
  styleUrls: ['./museum-form.component.css'],
})
export class MuseumFormComponent implements OnInit {
  countries: Country[] = [];
  cities: City[] = [];

  regions: string[] = []; 

  name = '';

  data!: object;
  museum!:object;

  selectedCountry = '';
  selectedCity = '';
  city!: City | undefined;

  longitude!: string | undefined;
  latitude!: string | undefined;

  photos:File[]= [];
  

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.http
      .get<{ name: { common: string }; cca2: string }[]>(
        'https://restcountries.com/v3.1/region/europe'
      )
      .subscribe((res) => {
        res.forEach((e) => {
          this.countries.push(new Country(e.name.common, e.cca2));
        });

        this.countries = this.countries.sort((one, two) =>
          one.common < two.common ? -1 : 1
        );
        // console.log(this.countries);
      });
  }

  getCities() {
    this.cities= [];
    this.selectedCity = '';
    this.latitude ='';
    this.longitude = '';  
    this.regions = [];

    this.http
      .get<{ region: string }[]>(
        'http://localhost:9000/api/v1/proxy/regions/' + this.selectedCountry, {withCredentials: true}
      )
      .subscribe((res) => {
        res.forEach((e) => this.regions.push(e.region));

        this.fetchCities();
      });

    // console.log(this.regions);

  }

  fetchCities(){
    this.cities = [];
    this.regions.forEach((region) => {
      this.http
        .get<City[]>(
          'http://localhost:9000/api/v1/proxy/cities/' + this.selectedCountry + '/' + region, {withCredentials: true} )
        .subscribe((res) => {
          // console.log(res)

          res.forEach(e => this.cities.push(new City(e.city,e.longitude ,e.latitude)));

          this.cities.sort((a,b) => a.city < b.city ? -1 : 1);
        });
    });
  }
  

  getCityInfo(){
   this.city = this.cities.find(e => e.city === this.selectedCity);
  
   this.latitude = this.city?.latitude;
   this.longitude = this.city?.longitude;
  }


  getDetails(form:NgForm){
    this.data = form.value;
     this.name = form.value.name;
    // console.log(this.data);    
    
  }

  getLocationInfo(form:NgForm){
    let countryName = this.countries.find(e => e.cca2 === this.selectedCountry)?.common;
    
    this.museum = {
      ... this.data,
      latitude: this.latitude,
      longitude: this.longitude,
      address: form.value.address,
      city: this.city?.city,
      country: countryName,

    }
    // console.log(this.museum);
  }

  submitData(form:NgForm){
  
    this.museum = {
      ... this.museum,
      presentation : form.value.video
    }

    console.log(this.museum);

    console.log(this.photos);
    
    if(this.photos.length >= 5 && this.photos.length <= 10){

      let formData : FormData = new FormData();
      
      for(let file of this.photos){ 
        formData.append('file', file);
        this.http.post('http://localhost:9000/api/v1/museums/upload/' + this.name+'/', formData, { withCredentials: true})
                 .subscribe(res => console.log(res))      
      };


    }
    
    this.http.post('http://localhost:9000/api/v1/museums',this.museum, {withCredentials: true})
    .subscribe(res => {
      console.log(res);
      
    }, err => {
      console.log(err);
      
    });
  }



  submitForm(form: NgForm){
    // console.log(form.value);
    let countryName = this.countries.find(e => e.cca2 === this.selectedCountry)?.common;

    let museum = {
      ... form.value,
      latitude: this.latitude,
      longitude: this.longitude,
      city: this.city?.city,
      country: countryName,
    }

    console.log(museum);
  
    this.http.post('http://localhost:9000/api/v1/museums',museum, {withCredentials: true})
             .subscribe(res => {
               console.log(res);
               
             }, err => {
               console.log(err);
               
             });
  }


  onPhotosSelected(event: any){
    this.photos = event.target.files;
   }
 
}


export class Country {
  constructor(public common: string, public cca2: string) {}
}

export class City {
  constructor( public city: string, public longitude: string , public latitude: string){

  }
}
