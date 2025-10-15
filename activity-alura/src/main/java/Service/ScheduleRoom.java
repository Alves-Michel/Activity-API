package Service;

import Repository.ReservationRepository;
import Repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class ScheduleRoom {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    public CreateReservation (Long roomId, LocalDateTime start, LocalDateTime end){
return null;

    }


}
