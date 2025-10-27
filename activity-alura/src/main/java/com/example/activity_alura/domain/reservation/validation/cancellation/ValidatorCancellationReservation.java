package com.example.activity_alura.domain.reservation.validation.cancellation;


import com.example.activity_alura.domain.reservation.CancellationReservationDTO;
import com.example.activity_alura.repository.ReservationRepository;
import com.example.activity_alura.infra.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("ValidatorCancellationReservation")
public class ValidatorCancellationReservation implements ValidatorCancellation {

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public void validate(CancellationReservationDTO cancellationReservationDTO) {
        var reserve = reservationRepository.findById(cancellationReservationDTO.reservationId())
                .orElseThrow(() -> new ValidationException("Reservation not found"));

        var now = LocalDateTime.now();
        long differenceMinutes = Duration.between(now, reserve.getStart()).toMinutes();

        if (differenceMinutes < 120) {
            throw new ValidationException(
                    "Invalid cancellation: cancellation must be at least 2 hours before start"
            );
        }
    }
}
