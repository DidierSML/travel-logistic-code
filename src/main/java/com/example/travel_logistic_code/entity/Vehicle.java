package com.example.travel_logistic_code.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vehicles")
public class Vehicle {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle vehicle)) return false;
        return Objects.equals(getLicensePlate(), vehicle.getLicensePlate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLicensePlate());
    }
}
