package com.example.travel_logistic_code.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UserRequestDTO(
                                  @NotBlank String name,
                                  @NotBlank String lastName) {
}
