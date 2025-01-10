package com.example.travel_logistic_code.dto.response;

public record PassengerResponseDTO(
                                   Long passengerId,
                                   String fullName) {

    //Constructor Personalizado
    public PassengerResponseDTO (Long passengerId, String name, String lastName) {
        this(passengerId,fullName(name, lastName));
    }

    //Método para concatenar
    private static String fullName(String name, String lastName) {
        return name + " " + lastName;
    }
}
