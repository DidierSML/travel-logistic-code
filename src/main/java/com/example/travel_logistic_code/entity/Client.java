package com.example.travel_logistic_code.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Client extends User{

    private String loyaltyCardNumber;

    @OneToMany(mappedBy = "client")
    private List<Trip> trips = new ArrayList<>();
}
