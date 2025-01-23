package com.example.travel_logistic_code.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;

public record DriverResponse(

        Long driverId,
        String fullName,
        String email,
        String licenseNumber,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        String licenseExpiryDate
) {

    // Constructor personalizado para concatenar
    public DriverResponse(Long driverId, String name, String lastName, String email, String licenseNumber, String licenseExpiryDate) {
        this(driverId, fullName(name, lastName), email, licenseNumber,licenseExpiryDate);
    }

    // Método para concatenar el nombre y el apellido
    private static String fullName(String name, String lastName) {
        return name + " " + lastName;
    }
}

