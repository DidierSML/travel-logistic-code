package com.example.travel_logistic_code.dto.newDTOs;

public record ClientRequest(

        UserRequest userRequest,
        String loyaltyCardNumber
) {
}
