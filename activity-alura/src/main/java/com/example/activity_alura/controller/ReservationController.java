package com.example.activity_alura.controller;

import com.example.activity_alura.domain.reservation.DataDetailsReservation;
import com.example.activity_alura.domain.reservation.ReservationDTO;
import com.example.activity_alura.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reserve")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @PostMapping("/register")
    public ResponseEntity<DataDetailsReservation> registerReserve(@RequestBody ReservationDTO body) {
        DataDetailsReservation reservation = reservationService.reserve(body);

        return ResponseEntity.ok(reservation);
    }
}
