package domain.room;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long roomId;

    private String roomName;

    private Integer roomCapacity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoomStats roomStats;

    private Integer roomNumber;
}
