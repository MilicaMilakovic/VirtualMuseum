package net.etfbl.ip.vm.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "tour")
public class TourEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "start")
    private Timestamp start;
    @Basic
    @Column(name = "duration")
    private Double duration;
    @Basic
    @Column(name = "price")
    private BigDecimal price;
    @Basic
    @Column(name = "active")
    private Boolean active = true;

    @ManyToOne
//    @JsonIgnore
    @JoinColumn(name = "museum_id", referencedColumnName = "id", nullable = false)
    private MuseumEntity museum;
    @OneToMany(mappedBy = "tour", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<TicketEntity> tickets;

}
