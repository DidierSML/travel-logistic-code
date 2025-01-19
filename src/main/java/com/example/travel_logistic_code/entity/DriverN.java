package com.example.travel_logistic_code.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class DriverN extends User {

    private String licenseNumber;
    private LocalDate licenseExpiryDate;

    @OneToMany(mappedBy = "driver")
    private List<Trip> trips = new ArrayList<>();
}
