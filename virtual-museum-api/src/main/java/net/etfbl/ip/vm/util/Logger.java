package net.etfbl.ip.vm.util;

import net.etfbl.ip.vm.models.entities.Log;
import net.etfbl.ip.vm.services.LoggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@Component
public class Logger implements HandlerInterceptor {
    @Autowired
    private LoggingService loggingService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        final String ip = LoggingUtils.getClientIpAddress();
        final String url = LoggingUtils.getRequestUrl();
        final String page = LoggingUtils.getRequestUri();
        final String refererPage = LoggingUtils.getRefererPage();
        final String queryString = LoggingUtils.getPageQueryString();
        final String userAgent = LoggingUtils.getUserAgent();
        final String requestMethod = LoggingUtils.getRequestMethod();
        final LocalDateTime timestamp = LocalDateTime.now();


        Log log = new Log(ip,url,page,refererPage,queryString,userAgent,requestMethod,timestamp);
        loggingService.log(log);
        return true;
    }
}