package com.example.travel_logistic_code.exception;

public class ReservationNotFoundException extends RuntimeException{

    public ReservationNotFoundException(String message){
        super(message);
    }
}
