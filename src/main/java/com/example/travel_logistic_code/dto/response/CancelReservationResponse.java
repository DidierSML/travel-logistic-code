package com.example.travel_logistic_code.dto.response;

import java.time.LocalDateTime;

public record CancelReservationResponse(

        Long reservationId,
        String status,
        String message,
        LocalDateTime cancellationDate
) {
}
