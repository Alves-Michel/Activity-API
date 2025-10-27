package com.example.activity_alura.domain.reservation.validation.scheduling;

import com.example.activity_alura.domain.reservation.ReservationDTO;
import com.example.activity_alura.infra.exception.ValidationException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("ValidateScheduleAdvance")
public class ValidateScheduleAdvance implements ValidatorReservationRoom {
    public void validate(ReservationDTO reservationDTO) {
        var dateReserve = LocalDateTime.of(reservationDTO.date(), reservationDTO.start());
        var now = LocalDateTime.now();
        var differenceMinutes = Duration.between(now, dateReserve).toMinutes();

        if (differenceMinutes < 60) {
            throw  new ValidationException("The reservation date is less than 60 minutes");
        }

    }
}
