package com.example.travel_logistic_code.repository;

import com.example.travel_logistic_code.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository <Driver,Long> {

    boolean existsByLicenseNumber (String licenseNumber);
}
