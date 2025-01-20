package com.example.travel_logistic_code.dto.newDTOs;


import java.time.LocalDateTime;

public record ReservationRequest(

        Long clientId,
        Long driverId,
        Long vehicleId,
        LocalDateTime reservationDate,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Double cost

) {
}
