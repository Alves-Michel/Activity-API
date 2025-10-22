package com.example.activity_alura.controller;

import com.example.activity_alura.domain.room.RoomDTO;
import com.example.activity_alura.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping("/register")
    public ResponseEntity<RoomDTO> createRoom(@RequestBody RoomDTO roomDTO) {
        return roomService.createRoom(roomDTO);
    }


    @GetMapping("/list")
    public ResponseEntity<List<RoomDTO>> getAllRooms() {
        var rooms = roomService.findAllRooms();
        return ResponseEntity.ok().body(rooms);
    }
}
