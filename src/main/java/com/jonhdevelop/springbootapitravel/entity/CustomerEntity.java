package com.jonhdevelop.springbootapitravel.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class CustomerEntity {

    @Id
    @Column(name = "dni")
    private String id;

    @Column(name = "full_name", length = 20)
    private String fullName;

    @Column(name = "credit_card", length = 20)
    private String creditCard;

    @Column(name = "total_flights")
    private Long totalFlights;

    @Column(name = "total_lodgings")
    private Long totalLodgings;

    @Column(name = "total_tours")
    private Long totalTour;

    @Column(name = "phone_number", length = 12)
    private String phoneNumber;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "customer",
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private Set<TicketEntity> tickets;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "customer",
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private Set<ReservationEntity> reservations;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "customer",
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private Set<TourEntity> tours;
}
