package com.example.travel_logistic_code.dto.response;

public record UserResponseDTO(
                                   Long userId,
                                   String fullName) {

    //Constructor Personalizado
    public UserResponseDTO(Long userId, String name, String lastName) {
        this(userId,fullName(name, lastName));
    }

    //Método para concatenar
    private static String fullName(String name, String lastName) {
        return name + " " + lastName;
    }
}
