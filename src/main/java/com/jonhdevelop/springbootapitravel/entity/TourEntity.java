package com.jonhdevelop.springbootapitravel.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "tour")
public class TourEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tour")
    private Long id;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "tourEntity",
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private Set<ReservationEntity> reservations;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "tourEntity",
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private Set<TicketEntity> tickets;

    @ManyToOne
    @JoinColumn(name = "id_customer")
    private CustomerEntity customer;


    public void  addTicket(TicketEntity ticket){
        if(Objects.isNull(this.tickets)) this.tickets = new HashSet<>();
        this.tickets.add(ticket);
    }

    public void removeTicket(UUID id){
        if(Objects.isNull(this.tickets)) this.tickets = new HashSet<>();
        this.tickets.removeIf(ticket -> ticket.getId().equals(id));
    }

    public void updateTickets(){
        this.tickets.forEach(ticket -> ticket.setTourEntity(this));
    }

    public void  addReservation(ReservationEntity reservation){
        if(Objects.isNull(this.reservations)) this.reservations = new HashSet<>();
        this.reservations.add(reservation);
    }

    public void removeReservations(UUID id){
        if (Objects.isNull(this.reservations)) this.reservations = new HashSet<>();
        this.reservations.removeIf(reservation -> reservation.getId().equals(id));
    }

    public void updateReservations(){
        this.reservations.forEach(reservation -> reservation.setTourEntity(this));
    }
}
