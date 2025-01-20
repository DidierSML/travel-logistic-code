package com.example.travel_logistic_code.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class DriverN extends User {

    @Column(nullable = false, unique = true)
    private String licenseNumber;

    @Column(nullable = false, unique = true)
    private LocalDate licenseExpiryDate;

    @OneToMany(mappedBy = "driver")
    private List<Reservation> reservations = new ArrayList<>();
}
