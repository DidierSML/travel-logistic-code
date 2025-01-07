package com.example.travel_logistic_code.model;

import lombok.Getter;

@Getter
public enum MessageConfirmation {

    CONFIRMED_TRAVEL("your travel has been confirmed successfully"),
    TRAVEL_UPDATED("your travel has been confirmed successfully"),
    TRAVEL_FETCH_SUCCESS("your travel has been confirmed successfully"),
    TRAVEL_NOT_FOUND("your travel has been confirmed successfully");

    private final String message;

    MessageConfirmation(String message){
        this.message = message;
    }

    public String getMessage (String name){
        return String.format("%s, %s", name, message);
    }


}
