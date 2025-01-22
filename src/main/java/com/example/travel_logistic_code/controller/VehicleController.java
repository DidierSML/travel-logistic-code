package com.example.travel_logistic_code.controller;

import com.example.travel_logistic_code.dto.request.VehicleRequest;
import com.example.travel_logistic_code.dto.response.VehicleResponse;
import com.example.travel_logistic_code.service.VehicleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicle")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }



    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public VehicleResponse save (@Valid @RequestBody VehicleRequest vehicleRequest){

        return vehicleService.save(vehicleRequest);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @ResponseStatus(HttpStatus.OK)
    public List<VehicleResponse> getAll (){

        return vehicleService.getAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @ResponseStatus(HttpStatus.OK)
    public VehicleResponse getById (@PathVariable (value = "id") Long id){

        return vehicleService.getById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public VehicleResponse update (@Valid @RequestBody VehicleRequest vehicleRequest,
                                   @PathVariable (value = "id") Long id){

        return vehicleService.update(vehicleRequest, id);

    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete (@PathVariable (value = "id") Long id){

        vehicleService.delete(id);

    }

}
