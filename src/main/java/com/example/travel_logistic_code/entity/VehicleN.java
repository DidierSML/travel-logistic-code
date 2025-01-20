package com.example.travel_logistic_code.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class VehicleN {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String licensePlate;
    private String brand;
    private String model;
    private int year;

    private String color;

    @OneToMany(mappedBy = "vehicle")
    private List<Trip> trips = new ArrayList<>();
}
