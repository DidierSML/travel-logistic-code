package com.example.travel_logistic_code.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate tripDate;
    private Double cost;

    @ManyToOne
    private DriverN driverN;

    @ManyToOne
    private Client client;

    private VehicleN vehicle;
}
