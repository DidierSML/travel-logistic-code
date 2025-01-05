package com.example.travel_logistic_code.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MessageConfirmation {

    CONFIRMED_TRAVEL("your travel has been confirmed successfully");

    private final String message;

    public String getMessage (String name){
        return String.format("%s, %s", name, message);
    }


}
