package com.example.travel_logistic_code.dto.newDTOs;

import com.example.travel_logistic_code.dto.newDTOs.UserResponse;

import java.time.LocalDate;

public record DriverResponse(

        UserResponse userResponse,
        String licenseNumber,
        LocalDate licenseExpiryDate
) {
}
