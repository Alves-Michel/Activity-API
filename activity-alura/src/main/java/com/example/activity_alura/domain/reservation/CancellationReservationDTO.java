package com.example.activity_alura.domain.reservation;

import jakarta.validation.constraints.NotNull;

public record CancellationReservationDTO (
        @NotNull
        Long reservationId,

        @NotNull
        CancellationReason reason
){}
