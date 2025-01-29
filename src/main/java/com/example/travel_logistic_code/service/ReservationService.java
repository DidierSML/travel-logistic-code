package com.example.travel_logistic_code.service;

import com.example.travel_logistic_code.dto.request.ReservationRequest;
import com.example.travel_logistic_code.dto.request.UpdateReservationReservationRequestAdmin;
import com.example.travel_logistic_code.dto.request.UpdateReservationReservationRequestClient;
import com.example.travel_logistic_code.dto.response.CancelReservationResponse;
import com.example.travel_logistic_code.dto.response.ReservationResponse;

import java.util.List;

public interface ReservationService {

    ReservationResponse save (ReservationRequest reservationRequestBase);
    List<ReservationResponse> getAll ();
    ReservationResponse getById (Long id);
    ReservationResponse updateByAdmin (UpdateReservationReservationRequestAdmin reservationRequest, Long id);
    ReservationResponse updateByClient (UpdateReservationReservationRequestClient reservationRequest, Long id);
    ReservationResponse update(ReservationRequest reservationRequestBase, Long id);
    CancelReservationResponse cancel (Long id);
    void delete (Long id);


}
