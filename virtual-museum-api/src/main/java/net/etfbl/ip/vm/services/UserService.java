package net.etfbl.ip.vm.services;

import net.etfbl.ip.vm.models.entities.UserEntity;
import net.etfbl.ip.vm.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<UserEntity> getAllUsers(){
        return userRepository.findAll();
    }

    public UserEntity registerUser(UserEntity user){
        return  userRepository.save(user);
    }

    public UserEntity loginUser(String username, String password){
        UserEntity user = userRepository.loginUser(username,password);
        if(user!=null){
//            userRepository.changeStatus(user.getId());
//            return userRepository.getById(user.getId());
        }
        return user;
    }

    public UserEntity getUserById(Integer userID) throws Exception{
        UserEntity user = userRepository.findById(userID).orElseThrow(()-> new Exception("User not found - id:"+userID));
        return  user;
    }

    public ArrayList<UserEntity> getRegisteredUsers(){
        ArrayList<UserEntity> registeredUsers = userRepository.getRegisteredUsers();
        return  registeredUsers;
    }

}
