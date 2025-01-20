package com.example.travel_logistic_code.dto.newDTOs;

import com.example.travel_logistic_code.dto.newDTOs.UserResponse;


public record ClientResponse(
        UserResponse userResponse,
        String loyaltyCardNumber
) {
}
