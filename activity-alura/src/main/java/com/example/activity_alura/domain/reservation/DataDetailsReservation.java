package com.example.activity_alura.domain.reservation;

import java.time.LocalDate;
import java.time.LocalTime;

public record DataDetailsReservation(
        Long reservationId,
        String roomName,
        String userName,
        LocalDate date,
        LocalTime start,
        LocalTime end) {
}
