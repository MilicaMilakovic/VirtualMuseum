export class Weather {
    
    cityName: string = "";
    temperature: string = "";

    constructor(cityName: string, temperature: string){
        this.cityName = cityName;
        this.temperature = temperature;
    }
}