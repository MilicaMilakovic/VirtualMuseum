package net.etfbl.ip.vm.controllers;

import net.etfbl.ip.vm.models.entities.UserEntity;
import net.etfbl.ip.vm.services.UserService;
import net.etfbl.ip.vm.util.UserTokenContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserTokenContainer userTokenContainer;

    @GetMapping
    public ResponseEntity<List<UserEntity>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/register")
    public ResponseEntity<UserEntity> registerUser(@RequestBody UserEntity user){
        UserEntity newUser = userService.registerUser(user);
        return ResponseEntity.ok(newUser);
    }

    @PostMapping("/login")
    public ResponseEntity<UserEntity> logIn(@RequestBody UserEntity userEntity, HttpServletRequest request, HttpServletResponse response){

        UserEntity user = userService.loginUser(userEntity.getUsername(),userEntity.getPassword());
        if(user!=null)
        {

            HttpSession session = request.getSession();
//            System.out.println(session.getId());
            String token = String.valueOf(System.currentTimeMillis());

            Cookie cookie = new Cookie("token", token);
            cookie.setHttpOnly(true);
            cookie.setMaxAge(60 * 60);
            cookie.setSecure(false);
            cookie.setPath("/");
            response.addCookie(cookie);
             userTokenContainer.addUser(cookie);

            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/logout")
    public void logOut(HttpServletRequest request, @CookieValue String token){
//        System.out.println( token + " LOGOUT");
        userTokenContainer.removeUser(token);
        HttpSession session =  request.getSession();
//        System.out.println(session.getId());
        session.invalidate();
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable Integer id){
        try{
            UserEntity user = userService.getUserById(id);
            return ResponseEntity.ok(user);
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/registered")
    public ResponseEntity<Integer> getNumberOfRegisteredUsers(){
        ArrayList<UserEntity> registeredUsers = userService.getRegisteredUsers();
        if(registeredUsers == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(registeredUsers.size());
    }
}
