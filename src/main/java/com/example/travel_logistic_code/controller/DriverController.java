package com.example.travel_logistic_code.controller;

import com.example.travel_logistic_code.dto.request.DriverRequestDTO;
import com.example.travel_logistic_code.dto.response.DriverResponseDTO;
import com.example.travel_logistic_code.service.DriverService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/driver")
public class DriverController {

    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DriverResponseDTO save (@Valid DriverRequestDTO driverRequestDTO){

        return driverService.save(driverRequestDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<DriverResponseDTO> getAll (){

        return driverService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DriverResponseDTO getById (@PathVariable (value = "id") Long id){

        return driverService.getById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DriverResponseDTO update (@Valid DriverRequestDTO driverRequestDTO,
                                     @PathVariable (value = "id") Long id){

        return driverService.update(driverRequestDTO,id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete (@PathVariable (value = "id") Long id){

        driverService.delete(id);
    }
}
