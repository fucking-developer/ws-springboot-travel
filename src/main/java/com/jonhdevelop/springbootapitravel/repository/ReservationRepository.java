package com.jonhdevelop.springbootapitravel.repository;

import com.jonhdevelop.springbootapitravel.entity.ReservationEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ReservationRepository extends CrudRepository<ReservationEntity, UUID> {
}
