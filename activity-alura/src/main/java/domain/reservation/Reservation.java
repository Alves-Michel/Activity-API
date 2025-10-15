package domain.reservation;

import domain.room.Room;
import domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.sql.results.DomainResultCreationException;


import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reservationId;

    @ManyToOne(optional = false)// impede reservas sem usuário
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;

    // Relacionamento com a sala reservada
    @ManyToOne(optional = false)
    @JoinColumn(name = "roomId", referencedColumnName = "roomId")
    private Room room;

    // Data da reserva
    @Column(nullable = false)
    private LocalDateTime start;

    @Column(nullable = false)
    private LocalDateTime end;

    @Enumerated(EnumType.STRING) // STATUS DA RESERVA(ENUM CRIADO)
    @Column(nullable = false)
    private ReservationStats reservationStats;

    private void validateDate(LocalDateTime start, LocalDateTime end){
        if(start == null || end == null) throw new DomainResultCreationException("Date doesn't exist");
        if(!start.isBefore(end)) throw new DomainResultCreationException("Date starts before end");
    }

    private void valiateAttendees(int attendees){

        if(attendees <= 0) throw new DomainResultCreationException("Attendees must be positive");
    }

}
