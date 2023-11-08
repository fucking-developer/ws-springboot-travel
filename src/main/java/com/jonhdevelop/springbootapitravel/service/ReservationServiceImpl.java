package com.jonhdevelop.springbootapitravel.service;

import com.jonhdevelop.springbootapitravel.entity.ReservationEntity;
import com.jonhdevelop.springbootapitravel.repository.CustomerRepository;
import com.jonhdevelop.springbootapitravel.repository.HotelRepository;
import com.jonhdevelop.springbootapitravel.repository.ReservationRepository;
import com.jonhdevelop.springbootapitravel.request.ReservationRequest;
import com.jonhdevelop.springbootapitravel.response.HotelResponse;
import com.jonhdevelop.springbootapitravel.response.ReservationResponse;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Transactional
@Service
@Slf4j
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService{

    private final HotelRepository hotelRepository;
    private final CustomerRepository customerRepository;
    private final ReservationRepository reservationRepository;

    private static final BigDecimal charges_price_percentaje = BigDecimal.valueOf(0.20);


    @Override
    public ReservationResponse create(ReservationRequest request) {
        var hotel = hotelRepository.findById(request.getIdHotel()).orElseThrow();
        var customer = customerRepository.findById(request.getIdClient()).orElseThrow();
        var reservationToPersist = ReservationEntity.builder()
                .id(UUID.randomUUID())
                .hotelEntity(hotel)
                .customer(customer)
                .totalDays(request.getTotalDays())
                .dateReservation(LocalDateTime.now())
                .dateStart(LocalDate.now())
                .dateEnd(LocalDate.now().plusDays(request.getTotalDays()))
                .price(hotel.getPrice().add(hotel.getPrice().multiply(charges_price_percentaje)))
                .build();
        return entityToResponse(reservationRepository.save(reservationToPersist));
    }

    @Override
    public ReservationResponse read(UUID uuid) {
        return entityToResponse(this.reservationRepository.findById(uuid).orElseThrow());
    }

    @Override
    public ReservationResponse update(ReservationRequest request, UUID uuid) {

        ReservationEntity reservationToUpdate = reservationRepository.findById(uuid).orElseThrow();
        var hotel = hotelRepository.findById(request.getIdHotel()).orElseThrow();
        reservationToUpdate.setHotelEntity(hotel);
        reservationToUpdate.setTotalDays(request.getTotalDays());
        reservationToUpdate.setDateReservation(LocalDateTime.now());
        reservationToUpdate.setDateStart(LocalDate.now());
        reservationToUpdate.setDateEnd(LocalDate.now().plusDays(request.getTotalDays()));
        reservationToUpdate.setPrice(hotel.getPrice().add(hotel.getPrice().multiply(charges_price_percentaje)));

        return entityToResponse(reservationRepository.save(reservationToUpdate));


    }

    @Override
    public void delete(UUID uuid) {
        var reservationToDelete = this.reservationRepository.findById(uuid).orElseThrow();
        this.reservationRepository.delete(reservationToDelete);
    }

    private ReservationResponse entityToResponse(ReservationEntity reservation){
        var reservationResponse = new ReservationResponse();
        BeanUtils.copyProperties(reservation, reservationResponse);
        var hotelResponse = new HotelResponse();
        BeanUtils.copyProperties(reservation.getHotelEntity(), hotelResponse);
        reservationResponse.setHotelResponse(hotelResponse);
        return reservationResponse;
    }


}
