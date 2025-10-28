package com.example.activity_alura.service;

import com.example.activity_alura.domain.reservation.*;
import com.example.activity_alura.domain.reservation.validation.cancellation.ValidatorCancellation;
import com.example.activity_alura.domain.reservation.validation.scheduling.ValidatorReservationRoom;
import com.example.activity_alura.domain.room.Room;
import com.example.activity_alura.domain.room.RoomStats;
import com.example.activity_alura.domain.user.User;
import com.example.activity_alura.repository.ReservationRepository;
import com.example.activity_alura.repository.RoomRepository;
import com.example.activity_alura.repository.UserRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<ResponseReservationDTO> findAllReservation(){
        return  reservationRepository.findAll().stream()
                .map( reservation -> new ResponseReservationDTO(
                        reservation.getRoom().getRoomNumber(),
                        reservation.getRoom().getRoomName(),
                        reservation.getUser().getName(),
                        reservation.getDate(),
                        reservation.getStart(),
                        reservation.getEnd(),
                        reservation.getReservationStats()
                )).collect(Collectors.toList());
    }


    public void updateReservation(Long reservationId, ReservationDTO data){
        if(!userRepository.existsById(data.userId())){
            throw new ValidationException("User does not exist");
        }
        if(data.roomId() != null && !roomRepository.existsById(data.roomId())){
            throw new ValidationException("Room does not exist");
        }

        var reservation = reservationRepository.findById(reservationId);
        if(reservation.isPresent()){
            var reserve = reservation.get();
            Optional.ofNullable(data.roomId()).ifPresent(roomId -> {
                Room room = roomRepository.findById(roomId).orElseThrow(() -> new ValidationException("Room not found"));
            });
            Optional.ofNullable(data.userId()).ifPresent(userId -> {
                User user = userRepository.findById(userId).orElseThrow(() -> new ValidationException("User not found"));
            });
            Optional.ofNullable(data.date()).ifPresent(reserve::setDate);
            Optional.ofNullable(data.start()).ifPresent(reserve::setStart);
            Optional.ofNullable(data.end()).ifPresent(reserve::setEnd);
            Optional.ofNullable(data.stats()).ifPresent(reserve::setReservationStats);

            reservationRepository.save(reserve);
        }
    }

    public List<ResponseReservationDTO> searchReservation(String roomName, String roomNumber, String userUserName, String startDate, String endDate){
        List<Reservation> reservations = reservationRepository.findAll();
        if(reservations.isEmpty()){
            throw new ValidationException("Reservation not found");
        }

        return reservations.stream()
                .filter(r -> roomName == null || r.getRoom().getRoomName().toLowerCase().contains(roomName.toLowerCase()))
                .filter(r -> roomNumber == null || r.getRoom().getRoomNumber().equals(roomNumber))
                .filter(r -> userUserName == null || r.getUser().getUserName().toLowerCase().contains(userUserName.toLowerCase()))
                .filter(r -> startDate == null || r.getStart().toString().equals(startDate))
                .filter(r -> endDate == null || r.getEnd().toString().equals(endDate))
                .map(r -> new ResponseReservationDTO(
                        r.getRoom().getRoomNumber(),
                        r.getRoom().getRoomName(),
                        r.getUser().getName(),
                        r.getDate(),
                        r.getStart(),
                        r.getEnd(),
                        r.getReservationStats()
                )).collect(Collectors.toList());


    }

    public void cancelReservation(Long reservationId, CancellationReason reason){
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ValidationException("Reservation not found"));
        if (reservation.getReservationStats() == ReservationStats.CANCELLED){
            throw new ValidationException("Reservation already cancelled");
        }

        reservation.setReservationStats(ReservationStats.CANCELLED);
        reservation.setCancellationReason(reason);
        reservationRepository.save(reservation);

    }








}
