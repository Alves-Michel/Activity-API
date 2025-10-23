package com.example.activity_alura.domain.reservation.validacoes.scheduling;

import com.example.activity_alura.domain.reservation.ReservationDTO;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("ValidateScheduleAdvance")
public class ValidateScheduleAdvance implements ValidatorReservationRoom {
    public void validate(ReservationDTO reservationDTO) {
        var dateReserve = reservationDTO.date();
        var now = LocalDateTime.now();
        var differenceMinutes = Duration.between(dateReserve, now).toMinutes();

        if (differenceMinutes < 60) {
            throw  new ValidationException("The reservation date is less than 60 minutes");
        }

    }
}
