package com.example.travel_logistic_code.dto.response;

import com.example.travel_logistic_code.model.MessageConfirmation;

public record TravelResponseDTO (
                                 Long travelId,
                                 String vehicleModel,
                                 String driverName,
                                 String passengerName,
                                 String dayOfService,

                                 MessageConfirmation messageConfirmation) {

}
