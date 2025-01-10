package com.example.travel_logistic_code.dto.response;

public record TravelResponseDTO (
                                 Long travelId,
                                 String vehicleBrand,
                                 String driverFullName,
                                 String passengerFullName,
                                 String dayOfService,

                                 String messageConfirmation) {

    //Constructor Personalizado
    public TravelResponseDTO (Long travelId, String vehicleBrand,
                              String driverName, String driverLastName,
                              String passengerName, String passengerLastName,
                              String dayOfService, String messageConfirmation){

        this(travelId,vehicleBrand,
                fullName(driverName,driverLastName),
                fullName(passengerName, passengerLastName),
                dayOfService,messageConfirmation);

    }

    //Método que maneja las concatenaciones del Constructor personalizado
    private static String fullName (String name, String lastName){
        return name + " " + lastName;
    }



}
