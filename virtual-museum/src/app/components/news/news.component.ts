import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { parse } from 'rss-to-json';
import { RssFeedItem } from 'src/app/models/rss-feed-item.model';
import { RssFeed } from 'src/app/models/rss-feed.model';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-news',
  templateUrl: './news.component.html',
  styleUrls: ['./news.component.css'],
})
export class NewsComponent implements OnInit {
  rssFeed: RssFeed = new RssFeed('', '', '');
  rssNews: Array<RssFeedItem> = [];

  constructor(private http: HttpClient, private userService: UserService, private router: Router) {}

  ngOnInit(): void {

    if(typeof this.userService.user === 'undefined')
        this.router.navigate(['']);
        
    (async () => {
      var rss = await parse('http://localhost:9000/api/v1/rss', { withCredentials: true});

      this.rssFeed = new RssFeed(rss.title, rss.description, rss.link);
      
      for (let i = 0; i < rss.items.length; i++) {
        this.rssNews.push(
          new RssFeedItem(
            rss.items[i].title,
            rss.items[i].content,
            rss.items[i].link,
            rss.items[i].enclosures[0].url
          )
        );
      }

    })();
  }
}
