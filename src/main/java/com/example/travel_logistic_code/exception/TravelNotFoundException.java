package com.example.travel_logistic_code.exception;

public class TravelNotFoundException extends RuntimeException{

    public TravelNotFoundException (String message){
        super(message);
    }
}
