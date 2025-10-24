package com.example.activity_alura.repository;

import com.example.activity_alura.domain.reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {

    boolean existsByRoom_RoomIdAndDateAndStart(Long roomId, LocalDate date, LocalTime start);

    /*boolean existsConflict(long roomId, LocalDateTime start, LocalDateTime end);#*/
}
