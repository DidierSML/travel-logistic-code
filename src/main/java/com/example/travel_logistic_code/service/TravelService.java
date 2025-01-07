package com.example.travel_logistic_code.service;

import com.example.travel_logistic_code.dto.request.TravelRequestDTO;
import com.example.travel_logistic_code.dto.response.TravelResponseDTO;

import java.util.List;

public interface TravelService  {

    TravelResponseDTO save (TravelRequestDTO travelRequestDTO);
    List<TravelResponseDTO> getAll ();
    TravelResponseDTO getById (Long id);
    TravelResponseDTO update (TravelResponseDTO travelResponseDTO, Long id);
    void delete (Long id);


}
