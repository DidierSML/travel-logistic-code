package com.example.travel_logistic_code.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("Driver")
@Table(name ="drivers")
public class Driver extends User {

    @Column(nullable = false, unique = true)
    private String licenseNumber;

    @Column(nullable = false, unique = true)
    private LocalDate licenseExpiryDate;

    @OneToMany(mappedBy = "driver")
    private List<Reservation> reservations = new ArrayList<>();

    //Equals & HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Driver driver)) return false;
        return Objects.equals(getLicenseNumber(), driver.getLicenseNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLicenseNumber());
    }
}
