package com.example.travel_logistic_code.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReservationRequestSave
        (

                @NotNull Long clientId,
                @NotNull Long driverId,
                @NotNull Long vehicleId,
                @NotBlank String reservationDate,
                ReservationRequestDates reservationRequestDates,
                @NotNull Double cost

        ) {
}
//@NotNull private Long clientId;
//        @NotNull private Long driverId;
//        @NotNull private Long vehicleId;
//        @NotBlank private String reservationDate;
//        @NotBlank private String startDate;
//        @NotBlank private String endDate;
//        @NotNull private Double cost;