package com.jonhdevelop.springbootapitravel.entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hotel")
public class HotelEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_hotel")
    private Long id;

    @Column(length = 50)
    private String name;

    @Column(length = 50)
    private String address;

    private Long rating;

    private BigDecimal price;


    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "hotelEntity",
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private Set<ReservationEntity> reservations;

}


