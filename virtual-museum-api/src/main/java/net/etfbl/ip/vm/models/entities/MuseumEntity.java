package net.etfbl.ip.vm.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Table(name = "museum")
public class MuseumEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "address")
    private String address;
    @Basic
    @Column(name = "phone")
    private String phone;
    @Basic
    @Column(name = "city")
    private String city;
    @Basic
    @Column(name = "country")
    private String country;
    @Basic
    @Column(name = "type")
    private String type;
    @Basic
    @Column(name = "image")
    private String image;
    @Basic
    @Column(name = "active")
    private Boolean active = true;
    @Basic
    @Column(name = "latitude")
    private BigDecimal latitude;
    @Basic
    @Column(name = "longitude")
    private BigDecimal longitude;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "presentation")
    private String presentation = "";
    @OneToMany(mappedBy = "museum")
    @JsonIgnore
    private List<TourEntity> tours;

}
