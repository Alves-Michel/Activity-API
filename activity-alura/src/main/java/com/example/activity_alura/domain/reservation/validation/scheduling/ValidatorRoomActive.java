package com.example.activity_alura.domain.reservation.validation.scheduling;

import com.example.activity_alura.domain.reservation.ReservationDTO;
import com.example.activity_alura.domain.room.RoomStats;
import com.example.activity_alura.infra.exception.ValidationException;
import com.example.activity_alura.repository.RoomRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatorRoomActive implements ValidatorReservationRoom {

    @Autowired
    private RoomRepository roomRepository;

    public void validate(ReservationDTO reservationDTO) {

        if (reservationDTO.roomId() == null){
            throw  new ValidationException("Room ID is required");
        }

        var room = roomRepository.findByRoomId(reservationDTO.roomId())
                .orElseThrow(() -> new ValidationException("Room not found"));

        if (room.getRoomStats() == RoomStats.DISABLED) {
            throw new ValidationException("Room is not active");
        }

    }
}
