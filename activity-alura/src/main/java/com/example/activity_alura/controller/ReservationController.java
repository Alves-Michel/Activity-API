package com.example.activity_alura.controller;

import com.example.activity_alura.domain.reservation.CancellationReservationDTO;
import com.example.activity_alura.domain.reservation.DataDetailsReservation;
import com.example.activity_alura.domain.reservation.ReservationDTO;
import com.example.activity_alura.domain.reservation.ResponseReservationDTO;
import com.example.activity_alura.infra.exception.ValidationException;
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

    @GetMapping("/search")
    public ResponseEntity<List<ResponseReservationDTO>> searchReservations(
            @RequestParam(required = false) String roomName,
            @RequestParam(required = false) String rooNumber,
            @RequestParam(required = false) String userUserName,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate


    ) {

        List<ResponseReservationDTO> reservationDTO = reservationService.searchReservation(roomName, rooNumber, userUserName, startDate, endDate);
        return ResponseEntity.ok(reservationDTO);
    }

    @PutMapping("/cancel/{reservationId}")
    public ResponseEntity<String> cancelReservation(
            @PathVariable("reservationId") Long reservationId,@RequestBody CancellationReservationDTO dto
    ) {
        reservationService.cancelReservation(reservationId, dto.reason());
        return ResponseEntity.ok("reservation cancelled sucessfully");
    }
}
