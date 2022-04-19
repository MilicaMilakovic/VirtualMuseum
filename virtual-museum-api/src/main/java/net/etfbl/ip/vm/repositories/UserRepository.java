package net.etfbl.ip.vm.repositories;


import net.etfbl.ip.vm.models.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {

    @Query("select u from UserEntity u where u.username = :username and u.password = :password")
    UserEntity loginUser(String username, String password);

    @Query("select u from UserEntity u where u.approved = true ")
    ArrayList<UserEntity> getRegisteredUsers();

    @Modifying
    @Query("update UserEntity u set u.loggedIn=true where u.id = :id")
    void changeStatus(Integer id);
}
