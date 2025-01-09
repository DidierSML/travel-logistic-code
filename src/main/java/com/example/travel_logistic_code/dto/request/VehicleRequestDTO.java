package com.example.travel_logistic_code.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record VehicleRequestDTO(
                                @NotBlank String brand,
                                @NotBlank String color,
                                @NotBlank String licensePlate,
                                @NotNull Integer seats) {
}
