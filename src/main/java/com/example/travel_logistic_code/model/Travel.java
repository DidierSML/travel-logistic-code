package com.example.travel_logistic_code.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="travels")
public class Travel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTravel;

    @ManyToOne
    private Vehicle vehicle;
    @ManyToOne
    private Driver driver;
    @ManyToOne
    private Passenger passenger;

    private Day dayOfService;

    //Getters & Setters

    public Long getIdTravel() {
        return idTravel;
    }

    public void setIdTravel(Long idTravel) {
        this.idTravel = idTravel;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Day getDayOfService() {
        return dayOfService;
    }

    public void setDayOfService(Day dayOfService) {
        this.dayOfService = dayOfService;
    }
}
