package com.example.travel_logistic_code.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ClientRequest(

        @NotBlank UserRequest userRequest,
        @NotBlank String loyaltyCardNumber
) {
}
