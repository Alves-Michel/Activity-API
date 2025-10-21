package com.example.activity_alura.repository;

import com.example.activity_alura.domain.reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {



    /*boolean existsConflict(long roomId, LocalDateTime start, LocalDateTime end);#*/
}
