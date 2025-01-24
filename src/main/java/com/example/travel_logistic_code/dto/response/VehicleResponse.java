package com.example.travel_logistic_code.dto.response;

public record VehicleResponse(

            Long vehicleId,
            String licensePlate,
            String brand,
            String model,
            int year,
            int seats,
            String color,
            String status
) {
}
