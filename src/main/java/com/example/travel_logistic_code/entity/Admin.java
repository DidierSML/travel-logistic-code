package com.example.travel_logistic_code.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("Admin")
@Table(name ="Administrators")
public class Admin extends User{

    @Column(nullable = false, unique = true)
    private String adminCode;

    //Equals & HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Admin admin)) return false;
        return Objects.equals(getAdminCode(), admin.getAdminCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAdminCode());
    }
}
