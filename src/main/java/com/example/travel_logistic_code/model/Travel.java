package com.example.travel_logistic_code.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
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
}
