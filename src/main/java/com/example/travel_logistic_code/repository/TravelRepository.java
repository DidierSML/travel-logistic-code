package com.example.travel_logistic_code.repository;

import com.example.travel_logistic_code.model.Travel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelRepository extends JpaRepository<Travel,Long> {
}
