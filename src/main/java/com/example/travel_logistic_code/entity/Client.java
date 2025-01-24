package com.example.travel_logistic_code.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="clients")
public class Client extends User{

    @Column(nullable = false, unique = true)
    private String loyaltyCardNumber;

    @OneToMany(mappedBy = "client")
    private List<Reservation> reservations = new ArrayList<>();

    //Equals & HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client client)) return false;
        return Objects.equals(getLoyaltyCardNumber(), client.getLoyaltyCardNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLoyaltyCardNumber());
    }
}
