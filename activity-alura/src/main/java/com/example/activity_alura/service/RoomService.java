package com.example.activity_alura.service;

import com.example.activity_alura.domain.room.Room;
import com.example.activity_alura.domain.room.RoomDTO;
import com.example.activity_alura.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public ResponseEntity createRoom(@RequestBody RoomDTO roomDTO) {
        Optional<Room> room = this.roomRepository.findByRoomName(roomDTO.roomName());

        if (room.isEmpty()) {
            Room newRoom = new Room();
            newRoom.setRoomName(roomDTO.roomName());
            newRoom.setRoomCapacity(roomDTO.roomCapacity());
            newRoom.setRoomNumber(roomDTO.roomNumber());
            newRoom.setRoomStats(roomDTO.roomStats());

            this.roomRepository.save(newRoom);

            return ResponseEntity.ok().body(newRoom);
        }

        return ResponseEntity.badRequest().build();
    }


    public List<RoomDTO> findAllRooms() {
        return roomRepository.findAll().stream()
                .map(room -> new RoomDTO(
                        room.getRoomName(),
                        room.getRoomCapacity(),
                        room.getRoomStats(),
                        room.getRoomNumber()
                )).collect(Collectors.toList());
    }

}
