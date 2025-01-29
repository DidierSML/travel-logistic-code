package com.example.travel_logistic_code.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReservationRequest
        (

                @NotNull Long clientId,
                @NotNull Long driverId,
                @NotNull Long vehicleId,
                @NotBlank String reservationDate,
                @NotBlank String startDate,
                @NotBlank String endDate,
                @NotNull Double cost

        ) {
}
