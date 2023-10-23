package com.jonhdevelop.springbootapitravel.repository;

import com.jonhdevelop.springbootapitravel.entity.ReservacionEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ReservationRepository extends CrudRepository<ReservacionEntity, UUID> {
}
