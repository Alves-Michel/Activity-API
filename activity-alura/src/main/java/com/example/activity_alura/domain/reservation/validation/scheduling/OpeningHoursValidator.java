package com.example.activity_alura.domain.reservation.validation.scheduling;

import com.example.activity_alura.domain.reservation.ReservationDTO;
import com.example.activity_alura.infra.exception.ValidationException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class OpeningHoursValidator implements ValidatorReservationRoom {

    public void validate(ReservationDTO reservationDTO){
        var dateReservation = reservationDTO.date();
        var startReservation = reservationDTO.start();
        var endReservation = reservationDTO.end();

        DayOfWeek dayOfWeek = dateReservation.getDayOfWeek();
        boolean isWeekend = dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;

        var afterOpening = startReservation.getHour() < 8;
        var beforeEnding = endReservation.getHour() > 21;

        if (endReservation.isBefore(startReservation)) {
            throw  new ValidationException("The end time must be after the start time.");
        }
        if (beforeEnding || afterOpening) {
            throw  new ValidationException("Reservations are only allowed between 8:00 and 21:00");
        }

        if (isWeekend) {
            throw  new ValidationException("Reservations are only allowed from Monday to Friday.");
        }








    }
}
