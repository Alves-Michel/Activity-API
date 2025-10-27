package com.example.activity_alura.service;

import com.example.activity_alura.domain.reservation.DataDetailsReservation;
import com.example.activity_alura.domain.reservation.Reservation;
import com.example.activity_alura.domain.reservation.ReservationDTO;
import com.example.activity_alura.domain.reservation.validation.cancellation.ValidatorCancellation;
import com.example.activity_alura.domain.reservation.validation.scheduling.ValidatorReservationRoom;
import com.example.activity_alura.domain.room.Room;
import com.example.activity_alura.domain.room.RoomStats;
import com.example.activity_alura.repository.ReservationRepository;
import com.example.activity_alura.repository.RoomRepository;
import com.example.activity_alura.repository.UserRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private List<ValidatorCancellation> validatorCancellations;

    @Autowired
    private List<ValidatorReservationRoom> validatorReservationRooms;


    public DataDetailsReservation reserve(ReservationDTO data){
        if(!userRepository.existsById(data.userId())){
            throw new ValidationException("User does not exist");
        }

        if(data.roomId() != null && !roomRepository.existsById(data.roomId())){
            throw new ValidationException("Room does not exist");
        }

        validatorReservationRooms.forEach(v -> v.validate(data));

        var user = userRepository.findById(data.userId())
                .orElseThrow(() -> new ValidationException("User does not exist"));;
        var room = chooseRoom(data);
        if(room == null){
            throw new ValidationException("Room does not exist");
        }
        var reserve = new Reservation(
                room,
                user,
                data.date(),
                data.start(),
                data.end(),
                data.stats()
        );
        reservationRepository.save(reserve);

        return new DataDetailsReservation(
                reserve.getReservationId(),
                room.getRoomName(),
                user.getName(),
                data.date(),
                data.start(),
                data.end()
        );
    }




    private Room chooseRoom(ReservationDTO data) {
        if (data.roomId() == null) {
            throw new ValidationException("Room ID must be provided");
        }

        // Busca a sala pelo ID
        Room room = roomRepository.findById(data.roomId())
                .orElseThrow(() -> new ValidationException("Room not found"));

        // Verifica se a sala está ativa
        if (room.getRoomStats() != RoomStats.ENABLED && room.getRoomStats() != RoomStats.AVAILABLE) {
            throw new ValidationException("Room is not active");
        }

        return room;
    }

}
