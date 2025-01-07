package com.example.travel_logistic_code.dto.request;

import jakarta.validation.constraints.NotBlank;

public record VehicleRequestDTO(
                                @NotBlank Long vehicleId,
                                @NotBlank String brand,
                                @NotBlank String color,
                                @NotBlank String licensePlate,
                                @NotBlank String seats) {
}
