package com.example.travel_logistic_code.dto.newDTOs;

import com.example.travel_logistic_code.dto.newDTOs.UserRequest;

import java.time.LocalDate;

public record DriverRequest(

        UserRequest userRequest,

        String licenseNumber,

        LocalDate licenseExpiryDate
) {
}
