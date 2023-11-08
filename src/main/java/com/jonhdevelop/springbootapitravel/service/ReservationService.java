package com.jonhdevelop.springbootapitravel.service;

import com.jonhdevelop.springbootapitravel.request.ReservationRequest;
import com.jonhdevelop.springbootapitravel.response.ReservationResponse;

import java.util.UUID;

public interface ReservationService extends CrudService<ReservationRequest, ReservationResponse, UUID>{
}
