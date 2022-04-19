import { Component, Input, OnInit } from '@angular/core';
import { RssFeedItem } from 'src/app/models/rss-feed-item.model';

@Component({
  selector: 'app-rss-item',
  templateUrl: './rss-item.component.html',
  styleUrls: ['./rss-item.component.css']
})
export class RssItemComponent implements OnInit {

  @Input('item') public item!: RssFeedItem;

  constructor() { }

  ngOnInit(): void {
    // console.log(this.item);
    
  }

}
