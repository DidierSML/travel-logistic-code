package com.example.travel_logistic_code.dto.request;


public record UpdateReservationRequestClient
        (
                String newStartDate,
                String newEndDate

        ) {
}
