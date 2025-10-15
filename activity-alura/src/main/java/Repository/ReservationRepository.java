package Repository;

import domain.reservation.Reservation;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {



    boolean existsConflict(long roomId, LocalDateTime start, LocalDateTime end);
}
