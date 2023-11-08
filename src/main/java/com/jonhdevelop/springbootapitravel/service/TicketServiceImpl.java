package com.jonhdevelop.springbootapitravel.service;

import com.jonhdevelop.springbootapitravel.entity.TicketEntity;
import com.jonhdevelop.springbootapitravel.repository.CustomerRepository;
import com.jonhdevelop.springbootapitravel.repository.FlyRepository;
import com.jonhdevelop.springbootapitravel.repository.TicketRepository;
import com.jonhdevelop.springbootapitravel.request.TicketRequest;
import com.jonhdevelop.springbootapitravel.response.FlyResponse;
import com.jonhdevelop.springbootapitravel.response.TicketResponse;
import com.jonhdevelop.springbootapitravel.util.TravelUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Transactional
@Service
@Slf4j
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService{

    private final FlyRepository flyRepository;
    private final CustomerRepository customerRepository;
    private final TicketRepository ticketRepository;

    private static final BigDecimal charger_price_percentaje = BigDecimal.valueOf(0.25);



    @Override
    public TicketResponse create(TicketRequest request) {
        var fly = flyRepository.findById(request.getIdFly()).orElseThrow();
        var customer = customerRepository.findById(request.getIdClient()).orElseThrow();
        var ticketToPersist = TicketEntity.builder()
                .id(UUID.randomUUID())
                .flyEntity(fly)
                .customer(customer)
                .price(fly.getPrice().add(fly.getPrice().multiply(charger_price_percentaje)))
                .purchaseDate(LocalDate.now())
                .departureDate(TravelUtil.getRandomSoon())
                .arrivalDate(TravelUtil.getRandomLatter())
                .build();
        var ticketPersist = ticketRepository.save(ticketToPersist);
        log.info("Ticket saved with id: {}", ticketPersist.getId());
        return this.entityToResponse(ticketPersist);
    }

    @Override
    public TicketResponse read(UUID uuid) {
        TicketEntity ticketEntity = ticketRepository.findById(uuid).orElseThrow();
        return this.entityToResponse(ticketEntity);
    }

    @Override
    public TicketResponse update(TicketRequest request, UUID uuid) {
        var ticketToUpdate = ticketRepository.findById(uuid).orElseThrow();
        var fly = flyRepository.findById(request.getIdFly()).orElseThrow();
        ticketToUpdate.setFlyEntity(fly);
        ticketToUpdate.setPrice(fly.getPrice().add(fly.getPrice().multiply(charger_price_percentaje)));
        ticketToUpdate.setDepartureDate(TravelUtil.getRandomSoon());
        ticketToUpdate.setArrivalDate(TravelUtil.getRandomLatter());
        var ticketUpdate = this.ticketRepository.save(ticketToUpdate);
        log.info("Ticket updated with id {}", ticketUpdate.getId());
        return this.entityToResponse(ticketUpdate);
    }

    @Override
    public void delete(UUID uuid) {
        var ticketToDelete = ticketRepository.findById(uuid).orElseThrow();
        this.ticketRepository.delete(ticketToDelete);
    }


    private TicketResponse entityToResponse(TicketEntity ticket){
        var response = new TicketResponse();
        BeanUtils.copyProperties(ticket, response);
        var flyResponse = new FlyResponse();
        BeanUtils.copyProperties(ticket.getFlyEntity(), flyResponse);
        response.setFly(flyResponse);
        return response;
    }

    @Override
    public BigDecimal findPrice(Long idFly) {
        var fly = this.flyRepository.findById(idFly).orElseThrow();
        return fly.getPrice().add(fly.getPrice().multiply(charger_price_percentaje));
    }

}
