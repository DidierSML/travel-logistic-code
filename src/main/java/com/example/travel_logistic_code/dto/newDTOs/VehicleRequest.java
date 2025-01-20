package com.example.travel_logistic_code.dto.newDTOs;

public record VehicleRequest(
                             String licensePlate,
                             String brand,
                             String model,
                             int year,
                             int seats,
                             String color) {

}
