package com.example.travel_logistic_code.service;


import com.example.travel_logistic_code.dto.request.VehicleRequestDTO;
import com.example.travel_logistic_code.dto.response.VehicleResponseDTO;
import com.example.travel_logistic_code.model.Vehicle;

import java.util.List;

public interface VehicleService  {

    VehicleResponseDTO save (VehicleRequestDTO vehicleRequestDTO);
    List<VehicleResponseDTO> getAll ();
    VehicleResponseDTO getById (Long id);
    VehicleResponseDTO update (VehicleRequestDTO vehicleRequestDTO, Long id);
    void delete (Long id);

}
