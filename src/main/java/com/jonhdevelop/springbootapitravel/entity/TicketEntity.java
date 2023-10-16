package com.jonhdevelop.springbootapitravel.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "ticket")
public class TicketEntity implements Serializable {

    @Id
    private UUID id;

    @Column(name = "departure_date")
    private LocalDateTime departureDate;

    @Column(name = "arrival_date")
    private LocalDateTime arrivalDate;

    @Column(name = "purchase_date")
    private LocalDate purchaseDate;

    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "fly_id")
    private FlyEntity flyEntity;

    @ManyToOne
    @JoinColumn(name = "tour_id")
    private TourEntity tourEntity;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;


}
