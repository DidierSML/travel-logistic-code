package com.example.travel_logistic_code.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CustomerRequestDTO(
                                  @NotBlank String name,
                                  @NotBlank String lastName) {
}
