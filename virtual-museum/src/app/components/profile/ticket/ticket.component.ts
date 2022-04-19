import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Ticket } from 'src/app/models/ticket.model';
import { SharedDataService } from 'src/app/services/shared-data.service';

@Component({
  selector: 'app-ticket',
  templateUrl: './ticket.component.html',
  styleUrls: ['./ticket.component.css']
})
export class TicketComponent implements OnInit {

  @Input('ticket') ticket!: Ticket;
  time = ' ';

  constructor(private sharedData: SharedDataService, private router: Router) { }

  ngOnInit(): void {
    // console.log(this.ticket);
    // console.log('----------------');
    this.time= new Date(this.ticket.tour.start).toLocaleString('en-GB',{year: 'numeric', month: 'numeric', day: 'numeric', hour: '2-digit', minute: '2-digit' });
    // this.tour.start = this.time;
    
  }

  log(){
    console.log(this.ticket.tour)
    this.sharedData.tour = this.ticket.tour;
  
  }

}
