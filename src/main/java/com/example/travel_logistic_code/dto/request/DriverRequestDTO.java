package com.example.travel_logistic_code.dto.request;

import jakarta.validation.constraints.NotBlank;

public record DriverRequestDTO(
                               @NotBlank Long driverId,
                               @NotBlank String name,
                               @NotBlank String lastName,
                               @NotBlank String driverLicense) {
}
