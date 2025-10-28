package com.example.activity_alura.repository;

import com.example.activity_alura.domain.reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {

    boolean existsByRoom_RoomIdAndDateAndStart(Long roomId, LocalDate date, LocalTime start);
    List<Reservation> findByreservationId(Long reservationId);

    @Query("""
    SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END
    FROM Reservation r
    WHERE r.room.roomId = :roomId
      AND r.date = :date
      AND (
          (r.start < :end AND r.end > :start)
      )
""")
    boolean existsOverlappingReservation(
            Long roomId,
            LocalDate date,
            LocalTime start,
            LocalTime end
    );

    /*boolean existsConflict(long roomId, LocalDateTime start, LocalDateTime end);#*/
}
