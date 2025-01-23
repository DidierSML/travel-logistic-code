package com.example.travel_logistic_code.dto.response;

public record AdminResponse(

        Long adminId,
        String fullName,
        String email,
        String adminCode ) {

    public AdminResponse (Long clientId, String name, String lastName, String email, String adminCode){
        this(clientId, fullName (name, lastName), email, adminCode);
    }

    private static String fullName (String name, String lastName){
        return name + " " + lastName;
    }

}
