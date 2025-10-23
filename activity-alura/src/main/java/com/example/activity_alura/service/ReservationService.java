package com.example.activity_alura.service;

import com.example.activity_alura.repository.ReservationRepository;
import com.example.activity_alura.repository.RoomRepository;
import com.example.activity_alura.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;


}
