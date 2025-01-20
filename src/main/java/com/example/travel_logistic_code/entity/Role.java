package com.example.travel_logistic_code.entity;

import com.example.travel_logistic_code.entity.enums.RoleType;
import jakarta.persistence.*;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,unique = true)
    private RoleType roleName;
}
