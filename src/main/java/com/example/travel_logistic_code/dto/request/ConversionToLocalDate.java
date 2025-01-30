package com.example.travel_logistic_code.dto.request;

import java.time.LocalDate;

public record ConversionToLocalDate
                (
                        LocalDate startDate,
                        LocalDate endDate
                ) {
}
