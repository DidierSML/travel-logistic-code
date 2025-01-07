package com.example.travel_logistic_code.dto.request;

import jakarta.validation.constraints.NotBlank;

public record TravelRequestDTO(
                               @NotBlank Long travelId,
                               @NotBlank Long vehicleId,
                               @NotBlank Long driverId,
                               @NotBlank Long passengerId,
                               @NotBlank String dayOfService) {
}
