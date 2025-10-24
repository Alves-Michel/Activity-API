package com.example.activity_alura.domain.reservation.validacoes.scheduling;

import com.example.activity_alura.domain.reservation.ReservationDTO;
import com.example.activity_alura.repository.ReservationRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatorOtherReservationSameHours implements ValidatorReservationRoom {

    @Autowired
    private ReservationRepository reservationRepository;

    public void validate(ReservationDTO reservationDTO) {
        var ReserveHaveOtherSameHours = reservationRepository.existsByRoom_RoomIdAndDateAndStart(reservationDTO.roomId(), reservationDTO.date(),reservationDTO.start());
        if (ReserveHaveOtherSameHours) {
            throw new ValidationException("Reservation already exists same hours");
        }
    }
}
