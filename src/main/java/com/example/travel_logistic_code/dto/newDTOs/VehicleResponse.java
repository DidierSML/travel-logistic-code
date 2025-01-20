package com.example.travel_logistic_code.dto.newDTOs;

public record VehicleResponse(
            String licensePlate,
            String brand,
            String model,
            int year,
            int seats,
            String color
) {
}
