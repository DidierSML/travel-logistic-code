package com.example.travel_logistic_code.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;

public record DriverResponse(

        Long driverId,
        String fullName,
        String email,
        String licenseNumber,
        String role,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        String licenseExpiryDate,

        String status
) {

    // Constructor personalizado para concatenar
    public DriverResponse(Long driverId, String name, String lastName, String email,
                          String licenseNumber, String role, String licenseExpiryDate, String status) {

        this(driverId, fullName(name, lastName), email, licenseNumber, role, licenseExpiryDate, status);
    }

    // Método para concatenar el nombre y el apellido
    private static String fullName(String name, String lastName) {
        return name + " " + lastName;
    }
}

