package net.etfbl.ip.vm.controllers;


import net.etfbl.ip.vm.models.entities.PaymentDetails;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequestMapping("/api/v1/bank")
public class BankController {

    static RestTemplate restTemplate = new RestTemplateBuilder().build();
    String url = "http://localhost:8080/virtual-bank-v2/api/v1/payment";

//    @GetMapping
//    public String test(HttpServletRequest request, HttpServletResponse response) {
//        System.out.println("test");
////        return "test";
////        return restTemplate.getForObject("http://localhost:8080/virtual-bank-v2/api/v1/payment", String.class);
//        return "test";
//    }


    @PostMapping
    public ResponseEntity<String> doPayment(@RequestBody PaymentDetails paymentDetails){
        System.out.println(paymentDetails);

        HttpEntity<PaymentDetails> request = new HttpEntity<>(paymentDetails);

        System.out.println("do Payment");
        String res="";
       try{
           ResponseEntity<String> response = restTemplate
                   .exchange(url, HttpMethod.POST, request, String.class);

           System.out.println(response.getBody()+"  " + response.getStatusCode());
           res=response.getBody();

           return ResponseEntity.ok(res);

       } catch (Exception e){
           res = e.getMessage().substring(7,e.getMessage().length()-1);
           System.out.println(res);
           return ResponseEntity.badRequest().body(res);
       }


    }

}
