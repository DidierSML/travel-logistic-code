package com.example.travel_logistic_code.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UserRequest(

        @NotBlank String name,
        @NotBlank String lastName,
        @NotBlank String email,
        @NotBlank String password ){

}


