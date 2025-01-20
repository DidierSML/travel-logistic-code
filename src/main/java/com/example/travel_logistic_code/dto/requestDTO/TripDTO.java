package com.example.travel_logistic_code.dto.requestDTO;

import java.time.LocalDate;

public class TripDTO {

    private Long id;
    private LocalDate tripDate;
    private Double cost;
    private DriverNDTO driver;
    private ClientDTO clientDTO;
    private VehicleNDTO vehicle;
}
