package com.example.activity_alura.domain.reservation;

import com.example.activity_alura.domain.room.Room;
import com.example.activity_alura.domain.user.User;
import com.example.activity_alura.exception.DomainException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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

    private LocalDate date;

    @Column(name = "start_time",nullable = false)
    private LocalTime start;

    @Column(name = "end_time", nullable = false )
    private LocalTime end;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReservationStats reservationStats;

    @Column(nullable = true)
    @Enumerated(EnumType.STRING)
    private CancellationReason cancellationReason;

    @Column(nullable = false)
    private int attendees;


    public Reservation(Room room, User user, LocalDate date, LocalTime start, LocalTime end, ReservationStats reservationStats) {
        this.room = room;
        this.user = user;
        this.date = date;
        this.start = start;
        this.end = end;
        this.reservationStats = reservationStats;
        this.attendees = 0; // inicializa como zero
    }

}
