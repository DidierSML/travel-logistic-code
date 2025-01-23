package com.example.travel_logistic_code.repository;

import com.example.travel_logistic_code.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository <Admin,Long> {

    boolean existsByAdminCode (String adminCode);

}
