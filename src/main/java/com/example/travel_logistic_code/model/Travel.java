package com.example.travel_logistic_code.model;

import com.example.travel_logistic_code.model.enums.Day;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

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
    private Customer customer;

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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Day getDayOfService() {
        return dayOfService;
    }

    public void setDayOfService(Day dayOfService) {
        this.dayOfService = dayOfService;
    }
}
