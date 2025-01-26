package com.example.travel_logistic_code.dto.request;

import java.time.LocalDateTime;

public record UpdateReservationRequestClient
        (
                LocalDateTime newStartDate,
                LocalDateTime newEndDate
        ) {
}
