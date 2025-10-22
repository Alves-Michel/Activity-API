package com.example.activity_alura.domain.room;

public record RoomDTO (
        String roomName,
        Integer roomCapacity,
        RoomStats roomStats,
        Integer roomNumber
) {}
