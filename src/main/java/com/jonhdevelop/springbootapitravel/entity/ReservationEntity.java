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
@Table(name = "reservation")
public class ReservationEntity implements Serializable {

    @Id
    @Column(name = "id_reservation")
    private UUID id;

    @Column(name = "date_reservation")
    private LocalDateTime dateReservation;

    @Column(name = "date_start")
    private LocalDate dateStart;

    @Column(name = "date_end")
    private LocalDate dateEnd;

    @Column(name = "total_days")
    private Integer totalDays;

    @Column(name = "price")
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private HotelEntity hotelEntity;


    @ManyToOne
    @JoinColumn(name = "tour_id")
    private TourEntity tourEntity;


    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

}
