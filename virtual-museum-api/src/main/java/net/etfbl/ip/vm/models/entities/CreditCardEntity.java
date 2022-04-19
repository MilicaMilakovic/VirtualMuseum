package net.etfbl.ip.vm.models.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Data
@Entity
@Table(name = "credit_card")
public class CreditCardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "number")
    private String number;
    @Basic
    @Column(name = "type")
    private String type;
    @Basic
    @Column(name = "expirationDate")
    private String expirationDate;
    @Basic
    @Column(name = "pin")
    private String pin;
    @Basic
    @Column(name = "balance")
    private BigDecimal balance;
    @Basic
    @Column(name = "active")
    private Boolean active;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private UserEntity user;

}
