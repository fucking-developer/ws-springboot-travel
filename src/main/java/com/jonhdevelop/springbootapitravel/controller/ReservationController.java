package com.jonhdevelop.springbootapitravel.controller;

import com.jonhdevelop.springbootapitravel.request.ReservationRequest;
import com.jonhdevelop.springbootapitravel.response.ReservationResponse;
import com.jonhdevelop.springbootapitravel.service.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/reservation")
@AllArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<ReservationResponse> post(@RequestBody ReservationRequest request){
        return ResponseEntity.ok(reservationService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponse> get(@PathVariable UUID id){
        return ResponseEntity.ok(reservationService.read(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationResponse> put(@PathVariable UUID id, @RequestBody ReservationRequest request){
        return ResponseEntity.ok(reservationService.update(request, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        reservationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
