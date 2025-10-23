package com.example.activity_alura.repository;

import com.example.activity_alura.domain.room.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;
import java.util.UUID;


public interface RoomRepository extends JpaRepository<Room,String> {
    Optional<Room> findByRoomName(String roomName);
    Optional<Room> findByRoomId(Long roomId);

}
