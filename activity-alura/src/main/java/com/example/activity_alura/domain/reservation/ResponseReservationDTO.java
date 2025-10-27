package com.example.activity_alura.domain.reservation;

import java.time.LocalDate;
import java.time.LocalTime;

public record ResponseReservationDTO (
        Integer numberRoom,
        String nameRoom,
        String nameUser,
        LocalDate date,
        LocalTime start,
        LocalTime end,
        ReservationStats stats
) {

}
