package com.example.travel_logistic_code.dto.request;

import jakarta.validation.constraints.NotBlank;

public record AdminRequest(

        @NotBlank UserRequest userRequest,
        @NotBlank String adminCode
) {
}
