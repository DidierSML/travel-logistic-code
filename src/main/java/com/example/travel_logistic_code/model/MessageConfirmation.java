package com.example.travel_logistic_code.model;

import lombok.Getter;

@Getter
public enum MessageConfirmation {

    CONFIRMED_TRAVEL("your travel has been confirmed successfully");

    private final String message;

    MessageConfirmation(String message){
        this.message = message;
    }

    public String getMessage (String name){
        return String.format("%s, %s", name, message);
    }


}
