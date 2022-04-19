package net.etfbl.ip.vm.controllers;

import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URL;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequestMapping("/api/v1/rss")
public class RSSController {

    static RestTemplate restTemplate = new RestTemplateBuilder().build();
    @GetMapping
    public String getFeed() {
        return restTemplate.getForObject("https://www.huffpost.com/section/arts/feed", String.class);
    }
}
