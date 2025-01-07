package com.example.travel_logistic_code.dto.response;


public record VehicleResponseDTO (
                                  Long vehicleId,
                                  String brand,
                                  String color,
                                  String licensePlate,
                                  String seats) {
}
