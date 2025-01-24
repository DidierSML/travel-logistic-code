package com.example.travel_logistic_code.exception;


public enum MessageConfirmation {

    CONFIRMED_TRAVEL("your travel has been confirmed successfully"),
    TRAVEL_UPDATED("your travel has been updated successfully"),
    TRAVEL_FETCH_SUCCESS("your travel has been found successfully"),
    TRAVEL_NOT_FOUND("Reservation not found with id: ");

    private final String message;

    MessageConfirmation(String message){
        this.message = message;
    }

    public String getMessage (){
        return message;
    }


}
