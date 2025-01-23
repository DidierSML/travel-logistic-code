package com.example.travel_logistic_code.repository;

import com.example.travel_logistic_code.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository <Client,Long> {

}
