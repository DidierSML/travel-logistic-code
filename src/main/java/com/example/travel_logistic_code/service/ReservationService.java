package com.example.travel_logistic_code.service;

import com.example.travel_logistic_code.dto.request.ReservationRequest;
import com.example.travel_logistic_code.dto.response.ReservationResponse;

import java.util.List;

public interface ReservationService {

    ReservationResponse save (ReservationRequest reservationRequest);
    List<ReservationResponse> getAll ();
    ReservationResponse getById (Long id);
    ReservationResponse update (ReservationRequest reservationRequest, Long id);
    void delete (Long id);


}
