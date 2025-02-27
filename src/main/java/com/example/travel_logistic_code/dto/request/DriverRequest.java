package com.example.travel_logistic_code.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record DriverRequest(

        @Valid UserRequest userRequest,

        @NotBlank String licenseNumber,

        @NotBlank String licenseExpiryDate
) {
}
