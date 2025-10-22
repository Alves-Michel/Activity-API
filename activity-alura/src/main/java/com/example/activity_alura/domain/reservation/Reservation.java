package com.example.activity_alura.domain.reservation;

import com.example.activity_alura.domain.room.Room;
import com.example.activity_alura.domain.user.User;
import com.example.activity_alura.exception.DomainException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @ManyToOne(optional = false)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "roomId", referencedColumnName = "roomId")
    private Room room;

    @Column(name = "start_time",nullable = false)
    private LocalDateTime start;

    @Column(name = "end_time", nullable = false )
    private LocalDateTime end;

    private boolean existsConflict;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReservationStats reservationStats;

    @Column(nullable = false)
    private int attendees;

    // --------------------------------------------
    // ✅ Construtor de domínio com validações
    // --------------------------------------------
    public Reservation(User user, Room room, LocalDateTime start, LocalDateTime end, int attendees, ReservationStats status) {
        validateDates(start, end);
        validateAttendees(attendees);

        if (room == null) throw new DomainException("A sala não pode ser nula.");
        if (user == null) throw new DomainException("O usuário não pode ser nulo.");
        //if (!room.isActive()) throw new DomainException("Não é possível reservar uma sala inativa.");
        if (room.getRoomCapacity() <= 0) throw new DomainException("A capacidade da sala deve ser positiva.");
        if (attendees > room.getRoomCapacity()) throw new DomainException("Número de participantes excede a capacidade da sala.");

        this.user = user;
        this.room = room;
        this.start = start;
        this.end = end;
        this.attendees = attendees;
        this.reservationStats = status;
    }

    // --------------------------------------------
    // Validações privadas de domínio
    // --------------------------------------------
    private void validateDates(LocalDateTime start, LocalDateTime end) {
        if (start == null || end == null) {
            throw new DomainException("Datas de início e fim são obrigatórias.");
        }
        if (!start.isBefore(end)) {
            throw new DomainException("A data de início deve ser anterior à data de fim.");
        }
    }

    private void validateAttendees(int attendees) {
        if (attendees <= 0) {
            throw new DomainException("O número de participantes deve ser positivo.");
        }
    }
}
