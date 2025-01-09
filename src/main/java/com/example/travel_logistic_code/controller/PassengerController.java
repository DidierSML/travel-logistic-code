package com.example.travel_logistic_code.controller;

import com.example.travel_logistic_code.dto.request.PassengerRequestDTO;
import com.example.travel_logistic_code.dto.response.PassengerResponseDTO;
import com.example.travel_logistic_code.service.PassengerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/passenger")
public class PassengerController {

    private final PassengerService passengerService;

    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PassengerResponseDTO save (@Valid @RequestBody PassengerRequestDTO passengerRequestDTO){

        return passengerService.save(passengerRequestDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PassengerResponseDTO> getAll (){

        return passengerService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PassengerResponseDTO getById (@PathVariable (value = "id") Long id){

        return passengerService.getById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PassengerResponseDTO update (@Valid @RequestBody PassengerRequestDTO passengerRequestDTO,
                                        @PathVariable (value = "id") Long id){

        return passengerService.update(passengerRequestDTO,id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete (@PathVariable (value = "id") Long id){

        passengerService.delete(id);
    }
}
