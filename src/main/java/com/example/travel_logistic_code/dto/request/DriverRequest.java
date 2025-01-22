package com.example.travel_logistic_code.dto.request;

import jakarta.validation.constraints.NotBlank;

public record DriverRequest(

        @NotBlank UserRequest userRequest,

        @NotBlank String licenseNumber,

        @NotBlank String licenseExpiryDate
) {
}
