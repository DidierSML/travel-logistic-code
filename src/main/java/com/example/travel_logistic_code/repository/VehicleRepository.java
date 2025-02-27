package com.example.travel_logistic_code.repository;

import com.example.travel_logistic_code.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle,Long> {

    boolean existsByLicensePlate(String licensePlate);
}
