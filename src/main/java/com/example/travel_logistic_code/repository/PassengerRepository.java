package com.example.travel_logistic_code.repository;

import com.example.travel_logistic_code.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerRepository extends JpaRepository <Passenger,Long> {
}
