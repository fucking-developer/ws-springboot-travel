package com.jonhdevelop.springbootapitravel.repository;

import com.jonhdevelop.springbootapitravel.entity.TicketEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TicketRepository extends CrudRepository<TicketEntity, UUID> {
}
