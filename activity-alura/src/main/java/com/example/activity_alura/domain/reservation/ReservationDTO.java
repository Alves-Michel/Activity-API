package com.example.activity_alura.domain.reservation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record ReservationDTO(
        Long roomId,
        Long userId,
        LocalDate date,
        LocalTime start,
        LocalTime end,
        ReservationStats stats
) {
}
