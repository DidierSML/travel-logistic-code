package com.example.travel_logistic_code.dto.response;

import com.example.travel_logistic_code.model.Day;
import com.example.travel_logistic_code.model.MessageConfirmation;

public record TravelResponseDTO (VehicleResponseDTO vehicleInfo,
                                 DriverResponseDTO driverInfo,
                                 PassengerResponseDTO passengerInfo,
                                 Day dayOfService,

                                 MessageConfirmation messageConfirmation) {

}
