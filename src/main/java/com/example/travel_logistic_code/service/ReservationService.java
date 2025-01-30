package com.example.travel_logistic_code.service;

import com.example.travel_logistic_code.dto.request.ReservationRequestSave;
import com.example.travel_logistic_code.dto.request.UpdateReservationRequestAdmin;
import com.example.travel_logistic_code.dto.request.ReservationRequestDates;
import com.example.travel_logistic_code.dto.response.CancelReservationResponse;
import com.example.travel_logistic_code.dto.response.ReservationResponse;

import java.util.List;

public interface ReservationService {

    ReservationResponse save (ReservationRequestSave reservationRequestSaveBase);
    List<ReservationResponse> getAll ();
    ReservationResponse getById (Long id);
    ReservationResponse updateByAdmin (UpdateReservationRequestAdmin reservationRequest, Long id);
    ReservationResponse updateByClient (ReservationRequestDates reservationRequest, Long id);
    ReservationResponse update(ReservationRequestSave reservationRequestSaveBase, Long id);
    CancelReservationResponse cancel (Long id);
    void delete (Long id);


}
