package net.etfbl.ip.vm.controllers;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequestMapping("/api/v1/proxy")
public class Proxy {

    static RestTemplate restTemplate = new RestTemplateBuilder().build();
    private final String API_KEY_1 = "dd938b558bac6440b85f830a8708fc42";
    private final String API_KEY_2 = "41a1d874d62683c699bbd30811d0c32f";

    @GetMapping("/regions/{cca2}")
    public String getRegions(@PathVariable String cca2) {
        String url = "http://battuta.medunes.net/api/region/" + cca2 + "/all/?key="+ API_KEY_1;

        return restTemplate.getForObject(url, String.class);
    }

    @GetMapping("/cities/{cca2}/{region}")
    public String getCities(@PathVariable String cca2, @PathVariable String region) {
        String url = "http://battuta.medunes.net/api/city/" + cca2 + "/search/?region=" + region + "&key="+ API_KEY_1;

        return restTemplate.getForObject(url, String.class);
    }
}
