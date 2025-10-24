package com.example.activity_alura.domain.reservation.validacoes.cancellation;

import com.example.activity_alura.domain.reservation.CancellationReason;
import com.example.activity_alura.domain.reservation.CancellationReservationDTO;

public interface ValidatorCancellation {

    void validate(CancellationReservationDTO cancellationReservationDTO);

}
