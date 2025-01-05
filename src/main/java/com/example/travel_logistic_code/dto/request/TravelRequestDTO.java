package com.example.travel_logistic_code.dto.request;

import com.example.travel_logistic_code.model.Day;
import jakarta.validation.constraints.NotBlank;

public record TravelRequestDTO(@NotBlank Long vehicleId,
                               @NotBlank Long driverId,
                               @NotBlank Long passengerId,
                               @NotBlank Day dayOfService) {
}
