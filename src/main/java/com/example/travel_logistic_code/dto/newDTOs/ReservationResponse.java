package com.example.travel_logistic_code.dto.newDTOs;

import java.time.LocalDateTime;

public record ReservationResponse (

        Long reservaId,
        LocalDateTime reservationDate,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Long driverId,
        String driverName,
        Long vehicleId,
        String vehicleBrand,
        String vehicleModel,
        Double cost,
        String status,
        String clientName

) {
}