package com.example.travel_logistic_code.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class VehicleN {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String licensePlate;

    @Column(nullable = false, unique = true)
    private String brand;

    @Column(nullable = false, unique = true)
    private String model;

    @Column(nullable = false, unique = true)
    private int year;

    @Column(nullable = false, unique = true)
    private Integer seats;

    @Column(nullable = false, unique = true)
    private String color;

    @OneToMany(mappedBy = "vehicle")
    private List<Reservation> reservations = new ArrayList<>();
}
