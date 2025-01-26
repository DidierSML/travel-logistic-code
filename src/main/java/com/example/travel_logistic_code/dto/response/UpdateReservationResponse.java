package com.example.travel_logistic_code.dto.response;

import com.example.travel_logistic_code.entity.enums.ReservationStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

public record UpdateReservationResponse
        (
                Long reservationId,
                @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
                String updatingDate,
                @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
                String startDate,
                @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
                String endDate,
                ReservationStatus status,
                String message
        ) {
}
