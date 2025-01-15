package com.example.travel_logistic_code.repository;

import com.example.travel_logistic_code.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository <User,Long> {
}
