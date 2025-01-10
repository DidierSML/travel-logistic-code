package com.example.travel_logistic_code.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TravelRequestDTO(
                               @NotNull Long vehicleId,
                               @NotNull Long driverId,
                               @NotNull Long passengerId,
                               @NotBlank String dayOfService) {
}
