package com.example.travel_logistic_code.controller;

import com.example.travel_logistic_code.dto.request.TravelRequestDTO;
import com.example.travel_logistic_code.dto.response.TravelResponseDTO;
import com.example.travel_logistic_code.service.PassengerService;
import com.example.travel_logistic_code.service.TravelService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/travel")
@AllArgsConstructor
public class TravelController {

    private final TravelService travelService;
    private final PassengerService passengerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TravelResponseDTO save (@Valid TravelRequestDTO travelRequestDTO){

        return travelService.save(travelRequestDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TravelResponseDTO> getAll (){

        return travelService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TravelResponseDTO getById (@PathVariable (value = "id") Long id){

        return travelService.getById(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TravelResponseDTO update (@Valid TravelRequestDTO travelRequestDTO,
                                     @PathVariable (value = "id") Long id){

        return travelService.update(travelRequestDTO,id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete (@PathVariable (value = "id") Long id){

        travelService.delete(id);
    }
}
