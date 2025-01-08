package com.example.travel_logistic_code.controller;

import com.example.travel_logistic_code.dto.request.VehicleRequestDTO;
import com.example.travel_logistic_code.dto.response.VehicleResponseDTO;
import com.example.travel_logistic_code.service.VehicleService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vehicle")
@AllArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleResponseDTO save (@Valid VehicleRequestDTO vehicleRequestDTO){

        return vehicleService.save(vehicleRequestDTO);
    }


}
