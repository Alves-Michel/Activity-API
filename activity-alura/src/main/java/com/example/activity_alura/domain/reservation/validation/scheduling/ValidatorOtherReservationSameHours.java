package com.example.activity_alura.domain.reservation.validation.scheduling;

import com.example.activity_alura.domain.reservation.ReservationDTO;
import com.example.activity_alura.repository.ReservationRepository;
import com.example.activity_alura.infra.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatorOtherReservationSameHours implements ValidatorReservationRoom {

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public void validate(ReservationDTO reservationDTO) {
        boolean hasConflict = reservationRepository.existsOverlappingReservation(
                reservationDTO.roomId(),
                reservationDTO.date(),
                reservationDTO.start(),
                reservationDTO.end()
        );

        if (hasConflict) {
            throw new ValidationException("This room already has a reservation during: "
                    + reservationDTO.start()
            + " until " + reservationDTO.end());
        }
    }
}
