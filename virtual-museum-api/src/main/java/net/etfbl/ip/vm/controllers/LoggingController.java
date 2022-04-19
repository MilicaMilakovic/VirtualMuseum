package net.etfbl.ip.vm.controllers;

import lombok.extern.slf4j.Slf4j;
import net.etfbl.ip.vm.configurations.MyAppConfiguration;
import net.etfbl.ip.vm.models.entities.LogEntity;
import net.etfbl.ip.vm.repositories.LogRepository;
import net.etfbl.ip.vm.services.LoggingService;
import net.etfbl.ip.vm.util.SessionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequestMapping("/api/v1/logger")
public class LoggingController {
    @Autowired
    ServletListenerRegistrationBean<SessionListener> sessionListener;
    @Autowired
    private LoggingService service;
    @Autowired
    private LogRepository logRepository;


    @GetMapping
    public ResponseEntity<Integer> getOnlineUsers(){
       Long n = service.getNumberOfActiveUsers();
       return ResponseEntity.ok(n.intValue());
    }


    @GetMapping (value="/download", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<Resource> downloadLogs(){
        Resource resource = null;

        try{
            String path = service.convertToPDF();
            resource = new InputStreamResource(new FileInputStream(path));
            return ResponseEntity.ok(resource);
        } catch (Exception e){
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(resource);
    }

    @GetMapping("/report")
    public ResponseEntity<List<LogEntity>> getHourlyReport(){
        return ResponseEntity.ok(service.getHourlyReport());
    }
}
