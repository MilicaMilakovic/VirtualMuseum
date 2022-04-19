package net.etfbl.ip.vm.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "first_name")
    private String firstName;
    @Basic
    @Column(name = "last_name")
    private String lastName;
    @Basic
    @Column(name = "username", unique = true)
    private String username;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "active", columnDefinition = "tinyint(1) default '1'")
    private Boolean active = true ;
    @Basic
    @Column(name = "logged_in", columnDefinition = "tinyint(1) default '0'")
    private Boolean loggedIn = false ;
    @Basic
    @Column(name = "role")
    private String role = "user" ;
    @Basic
    @Column(name = "token")
    private String token = "" ;
    @Basic
    @Column(name = "approved", columnDefinition = "tinyint(1) default '0'")
    private Boolean approved = false ;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<CreditCardEntity> creditCards;
    @OneToMany(mappedBy = "user")
//    @JsonIgnore
    private List<TicketEntity> tickets;

}
