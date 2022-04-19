package net.etfbl.ip.vm.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

    @Autowired
    private UserTokenContainer userTokenContainer;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
    {
        if ("OPTIONS".equals(request.getMethod()))  {
            response.setStatus(200);
            return true;
        }
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");
        String requestURI = request.getRequestURI();
        if("/api/v1/users/login".equals(requestURI) || "/api/v1/users/register".equals(requestURI) )
            return true;


        Cookie[] cookies =  request.getCookies();

        if(cookies!=null) {
            for (Cookie c : cookies) {
                if ("token".equals(c.getName())) {
                    String value = c.getValue();
                    if (userTokenContainer.checkIfExists(value))
                        return true;
                    response.setStatus(401);
                    return false;
                }
            }
        }
        response.setStatus(401);
        return false;
    }
}
