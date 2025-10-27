package com.example.activity_alura.controller;

import com.example.activity_alura.domain.reservation.DataDetailsReservation;
import com.example.activity_alura.domain.reservation.ReservationDTO;
import com.example.activity_alura.domain.reservation.ResponseReservationDTO;
import com.example.activity_alura.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/list")
    public ResponseEntity<List<ResponseReservationDTO>> findAllReservations() {
        var reserve = reservationService.findAllReservation();
        return ResponseEntity.ok(reserve);
    }

    @PutMapping("/update/{reservationId}")
    public ResponseEntity<Void> updateReservation(@PathVariable("reservationId") Long reservationId,
                                                  @RequestBody ReservationDTO body) {
        reservationService.updateReservation(reservationId, body);
        return ResponseEntity.ok().build();

    }
}
