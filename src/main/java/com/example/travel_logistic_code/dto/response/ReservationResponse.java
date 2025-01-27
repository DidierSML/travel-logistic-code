package com.example.travel_logistic_code.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;

public record ReservationResponse(

                Long reservationId,
                @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
                String reservationDate,
                @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
                String startDate,
                @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
                String endDate,
                Long driverId,
                String driverFullName,
                Long vehicleId,
                String vehicleBrand,
                String vehicleModel,
                Double cost,
                String status,
                String clientFullName,
                String message
) {

    public ReservationResponse(Long reservationId, String reservationDate,
                               String startDate, String endDate,
                               Long driverId, String driverName, String driverLastName,
                               Long vehicleId, String vehicleBrand, String vehicleModel,
                               Double cost, String status, String clientName, String clientLastName,
                               String message){

        this(reservationId, reservationDate,startDate,endDate,driverId,
                fullName(driverName,driverLastName),
                vehicleId,vehicleBrand, vehicleModel, cost, status,
                fullName (clientName, clientLastName), message);
    }

    private static String fullName (String name, String lastName){
        return name + " " + lastName;
    }
}