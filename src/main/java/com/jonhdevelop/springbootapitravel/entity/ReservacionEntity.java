package com.jonhdevelop.springbootapitravel.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "reservation")
public class ReservacionEntity implements Serializable {

    @Id
    @Column(name = "id_reservation")
    private UUID id;

    @Column(name = "date_reservation")
    private Date dateReservation;

    @Column(name = "date_start")
    private Date dateStart;

    @Column(name = "date_end")
    private Date dateEnd;

    @Column(name = "total_days")
    private Long totalDays;

    @Column(name = "price")
    private Long price;

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
