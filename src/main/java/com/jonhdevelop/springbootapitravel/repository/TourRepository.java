package com.jonhdevelop.springbootapitravel.repository;

import com.jonhdevelop.springbootapitravel.entity.TourEntity;
import org.springframework.data.repository.CrudRepository;

public interface TourRepository extends CrudRepository<TourEntity, Long> {
}
