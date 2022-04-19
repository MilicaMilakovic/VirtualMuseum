package net.etfbl.ip.vm.util;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import java.util.ArrayList;
import java.util.List;

@Component
@Data
public class UserTokenContainer {
    private List<Cookie> userTokens = new ArrayList<>();

    public void addUser(Cookie token)
    {
        userTokens.add(token);
    }

    public void removeUser(String token)
    {
        userTokens.removeIf(cookie -> token.equals(cookie.getValue()));

    }

    public boolean checkIfExists(String token)
    {
        for(Cookie cookie : userTokens){
            if( token.equals(cookie.getValue()))
                return true;
        }
        return false;
    }
}
