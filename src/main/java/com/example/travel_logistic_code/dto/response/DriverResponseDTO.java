package com.example.travel_logistic_code.dto.response;

public record DriverResponseDTO(
        Long driverId,
        String fullName,
        String driverLicense
) {

    // Constructor personalizado para concatenar
    public DriverResponseDTO(Long driverId, String name, String lastName, String driverLicense) {
        this(driverId, fullName(name, lastName), driverLicense);
    }

    // Método para concatenar el nombre y el apellido
    private static String fullName(String name, String lastName) {
        return name + " " + lastName;
    }
}


