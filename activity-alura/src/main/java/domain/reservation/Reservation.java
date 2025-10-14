package domain.reservation;

import domain.room.Room;
import domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;


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
    private LocalDate reservationDate;

    @Enumerated(EnumType.STRING) // STATUS DA RESERVA(ENUM CRIADO)
    @Column(nullable = false)
    private ReservationStats reservationStats;

}
