package com.example.travel_logistic_code.dto.request;

import com.example.travel_logistic_code.entity.enums.ReservationStatus;

public record UpdateReservationRequestAdmin
        (
                ReservationRequestDates reservationRequestDates,
                Long newDriverId,
                Long newVehicleId,
                ReservationStatus newStatus

        ) {
}
