package com.example.travel_logistic_code.service;

import com.example.travel_logistic_code.dto.request.PassengerRequestDTO;
import com.example.travel_logistic_code.dto.response.PassengerResponseDTO;

import java.util.List;

public interface PassengerService {

    PassengerResponseDTO save (PassengerRequestDTO passengerRequestDTO);
    List<PassengerResponseDTO> getAll ();
    PassengerResponseDTO getById (Long id);
    PassengerResponseDTO update (PassengerRequestDTO passengerRequestDTO, Long id);
    void delete (Long id);

}
