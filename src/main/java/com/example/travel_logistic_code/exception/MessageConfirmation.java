package com.example.travel_logistic_code.exception;


public enum MessageConfirmation {

    RESERVATION_CONFIRMED("your travel has been confirmed successfully"),
    RESERVATION_UPDATED("your travel has been updated successfully"),
    RESERVATION_FETCH_SUCCESS("your travel has been found successfully"),
    RESERVATION_NOT_FOUND("Reservation not found with id: ");

    private final String message;

    MessageConfirmation(String message){
        this.message = message;
    }

    public String getMessage (){
        return message;
    }


}
