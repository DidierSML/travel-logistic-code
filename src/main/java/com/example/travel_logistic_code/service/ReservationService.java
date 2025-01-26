package com.example.travel_logistic_code.service;

import com.example.travel_logistic_code.dto.request.ReservationRequest;
import com.example.travel_logistic_code.dto.response.CancelReservationResponse;
import com.example.travel_logistic_code.dto.response.SaveReservationResponse;

import java.util.List;

public interface ReservationService {

    SaveReservationResponse save (ReservationRequest reservationRequest);
    List<SaveReservationResponse> getAll ();
    SaveReservationResponse getById (Long id);
    SaveReservationResponse update (ReservationRequest reservationRequest, Long id);
    CancelReservationResponse cancel (Long id);
    void delete (Long id);


}
