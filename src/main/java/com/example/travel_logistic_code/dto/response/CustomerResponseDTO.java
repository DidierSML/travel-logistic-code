package com.example.travel_logistic_code.dto.response;

public record CustomerResponseDTO(
                                   Long userId,
                                   String fullName) {

    //Constructor Personalizado
    public CustomerResponseDTO(Long userId, String name, String lastName) {
        this(userId,fullName(name, lastName));
    }

    //Método para concatenar
    private static String fullName(String name, String lastName) {
        return name + " " + lastName;
    }
}
