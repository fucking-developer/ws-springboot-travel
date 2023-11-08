package com.jonhdevelop.springbootapitravel.service;

import com.jonhdevelop.springbootapitravel.request.TicketRequest;
import com.jonhdevelop.springbootapitravel.response.TicketResponse;

import java.math.BigDecimal;
import java.util.UUID;

public interface TicketService extends CrudService<TicketRequest, TicketResponse, UUID>{

    BigDecimal findPrice(Long idFly);
}
