package com.jonhdevelop.springbootapitravel.entity;

import com.jonhdevelop.springbootapitravel.util.AeroLine;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "fly")
public class FlyEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fly")
    private Long id;

    @Column(name = "origin_lat")
    private Double originLat;

    @Column(name = "origin_lng")
    private Double originLng;

    @Column(name = "destiny_lat")
    private Double destinyLat;

    @Column(name = "destiny_lng")
    private Double destinyLng;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "origin_name", length = 20)
    private String originName;

    @Column(name = "destiny_name", length = 20)
    private String destinyName;

    @Column(name = "aero_line", length = 20)
    @Enumerated(EnumType.STRING)
    private AeroLine aeroLine;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "flyEntity",
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private Set<TicketEntity> tickets;
}
