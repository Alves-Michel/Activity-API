package com.example.activity_alura.repository;

import com.example.activity_alura.domain.room.Room;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.UUID;


public interface RoomRepository extends JpaRepository<Room,UUID> {

}
