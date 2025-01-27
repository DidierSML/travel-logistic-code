package com.example.travel_logistic_code.dto.request;

import com.example.travel_logistic_code.entity.enums.ReservationStatus;

public record UpdateReservationRequestAdmin
        (
                String newStartDate,
                String newEndDate,
                Long newDriverId,
                Long newVehicleId,
                ReservationStatus newStatus
        ) {
}
