import { HttpClient } from '@angular/common/http';
import { Component, Inject, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user.model';
import { UserService } from 'src/app/services/user.service';
import { Chart, registerables } from 'node_modules/chart.js';
import { DOCUMENT } from '@angular/common';


@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  admin!: User;
  registeredUsers: number = 0;
  online: number = 0;
  data: ChartData[] = [];
  labels: string[] = [];
  values: any = [];
  chart : any = [];

  reportsLink= 'http://localhost:9000/api/v1/logger/download';

  link: string ="http://localhost:8080/virtual-museum-admin/?id=2&token=c84258e9c39059a89ab77d846ddab909";

  constructor(private userService: UserService, private router: Router, 
              private http: HttpClient,@Inject(DOCUMENT) private document: Document) {
                Chart.register(...registerables);
  }

  ngOnInit(): void {
    this.admin = this.userService.admin;

    if(typeof this.admin === 'undefined')
      this.router.navigate(['/login']);  

    this.link = "http://localhost:8080/virtual-museum-admin/?id=" + this.admin.id + "&token="+ this.admin.token;

    this.http.get<number>('http://localhost:9000/api/v1/users/registered', {withCredentials: true})
             .subscribe(res => this.registeredUsers = res,
              err => 
                this.router.navigate([''])
             );
    this.http.get<number>('http://localhost:9000/api/v1/logger', {withCredentials: true})
              .subscribe(res => this.online = res,
                err => 
                this.router.navigate(['']));

    this.http.get<ChartData[]>('http://localhost:9000/api/v1/logger/report', {withCredentials: true})
              .subscribe(res => {
              // console.log(res);
              this.data = res;
              this.data = this.data.slice(-24);
              console.log(this.data);
              
              this.drawChart();
            }, err => 
            this.router.navigate(['']));

  }

  drawChart(){
    // console.log("drawChart()");
    
    this.data.forEach(e => {
      let date = new Date(e.timestamp).toLocaleString('en-US', {hour: '2-digit', minute: '2-digit', hour12:false});    

      this.labels.push(date);
      this.values.push(e.online);

      // console.log(e);
      
      // console.log(date, e.online);
      // console.log('-----');
      
    });

    // this.labels.sort((a,b) =>  a>b ? 1 : -1 );
    // console.log(this.labels);
    let delayed = false;
    let ctx = this.document.getElementById("myChart") as HTMLCanvasElement;
    new Chart( ctx ,{
        type: 'bar',
        data: {
          labels: this.labels,
          datasets: [
            {
              label: "Number of online users in the past 24 hours",
              backgroundColor: ["#3e95cd", "#8e5ea2","#3cba9f","#e8c3b9","#c45850","#75A3B0"],
              data: this.values
            }
          ]
        },
        options: {
          animation: {
            onComplete: () => {
              delayed = true;
            },
            delay: (context: any) => {
              let delay = 0;
              if (context.type === 'data' && context.mode === 'default' && !delayed) {
                delay = context.dataIndex * 300 + context.datasetIndex * 100;
              }
              return delay;
            },
          },
          scales: {
            x: {
              stacked: true,
            },
            y: {
              stacked: true
            }
          }
        }  
      
    });

   
  }

}


export interface ChartData {
  id: number; 
  timestamp: string; 
  online: number;
}