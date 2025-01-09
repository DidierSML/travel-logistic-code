package com.example.travel_logistic_code.dto.request;

import jakarta.validation.constraints.NotNull;

public record DriverRequestDTO(
                               @NotNull String name,
                               @NotNull String lastName,
                               @NotNull String driverLicense) {
}
