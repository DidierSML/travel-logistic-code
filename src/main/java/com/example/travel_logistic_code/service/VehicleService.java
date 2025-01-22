package com.example.travel_logistic_code.service;


import com.example.travel_logistic_code.dto.request.VehicleRequest;
import com.example.travel_logistic_code.dto.response.VehicleResponse;

import java.util.List;

public interface VehicleService  {

    VehicleResponse save (VehicleRequest vehicleRequestDTO);
    List<VehicleResponse> getAll ();
    VehicleResponse getById (Long id);
    VehicleResponse update (VehicleRequest vehicleRequestDTO, Long id);
    void delete (Long id);

}
