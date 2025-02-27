package com.example.travel_logistic_code.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalException {

    @ExceptionHandler(ReservationNotFoundException.class)
    public ResponseEntity<String> handleTravelNotFoundException (ReservationNotFoundException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}
