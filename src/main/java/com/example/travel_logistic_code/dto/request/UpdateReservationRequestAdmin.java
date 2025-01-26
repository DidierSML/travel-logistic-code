package com.example.travel_logistic_code.dto.request;

import com.example.travel_logistic_code.entity.enums.ReservationStatus;

import java.time.LocalDateTime;

public record UpdateReservationRequestAdmin
        (
                LocalDateTime newStartDate,
                LocalDateTime newEndDate,
                Long newDriverId,
                Long newVehicleId,
                ReservationStatus newStatus
        ) {
}
