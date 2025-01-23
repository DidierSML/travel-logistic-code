package com.example.travel_logistic_code.dto.response;

public record AdminResponse(

        Long adminId,
        String fullName,
        String email,
        String role,
        String adminCode ) {

    public AdminResponse (Long clientId, String name, String lastName, String email, String role, String adminCode){
        this(clientId, fullName (name, lastName), email, role, adminCode);
    }

    private static String fullName (String name, String lastName){
        return name + " " + lastName;
    }

}
