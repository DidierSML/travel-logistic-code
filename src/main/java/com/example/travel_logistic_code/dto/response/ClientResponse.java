package com.example.travel_logistic_code.dto.response;


public record ClientResponse (
        Long clientId,
        String fullName,
        String email,
        String role,
        String loyaltyCardNumber) {

    public ClientResponse (Long clientId, String name, String lastName, String email, String role, String loyaltyCardNumber){
        this(clientId, fullName (name, lastName), email, role, loyaltyCardNumber);
    }

    private static String fullName (String name, String lastName){
        return name + " " + lastName;
    }
}
