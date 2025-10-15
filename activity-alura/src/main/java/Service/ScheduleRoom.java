package Service;

import Repository.ReservationRepository;
import Repository.RoomRepository;
import domain.reservation.Reservation;
import domain.reservation.ReservationStats;
import domain.room.Room;
import domain.user.User;
import exception.DomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ScheduleRoom {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    public Reservation createReservation(User user, UUID roomId, LocalDateTime start, LocalDateTime end, int attendees) {

        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new DomainException("Sala não encontrada."));

        //  Verifica conflito de horário
        boolean conflict = reservationRepository.existsConflict(room.getRoomId(), start, end);
        if (conflict) {
            throw new DomainException("Já existe uma reserva para este horário.");
        }


        Reservation reservation = new Reservation(
                user,
                room,
                start,
                end,
                attendees,
                ReservationStats.PENDING
        );

        return reservationRepository.save(reservation);
    }
}
