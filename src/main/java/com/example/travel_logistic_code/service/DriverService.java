package com.example.travel_logistic_code.service;

import com.example.travel_logistic_code.dto.request.DriverRequestDTO;
import com.example.travel_logistic_code.dto.response.DriverResponseDTO;

import java.util.List;

public interface DriverService  {

    DriverResponseDTO save (DriverRequestDTO driverRequestDTO);
    List<DriverResponseDTO> getAll ();
    DriverResponseDTO getById (Long id);
    DriverResponseDTO update (DriverRequestDTO driverRequestDTO, Long id);
    void delete (Long id);
}
