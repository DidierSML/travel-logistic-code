package com.example.travel_logistic_code.controller;

import com.example.travel_logistic_code.dto.request.ReservationRequest;
import com.example.travel_logistic_code.dto.response.ReservationResponse;
import com.example.travel_logistic_code.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservation")
public class TravelController {

    private final ReservationService reservationService;

    public TravelController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReservationResponse save (@Valid @RequestBody ReservationRequest reservationRequest){

        return reservationService.save(reservationRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ReservationResponse> getAll (){

        return reservationService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReservationResponse getById (@PathVariable (value = "id") Long id){

        return reservationService.getById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReservationResponse update (@Valid @RequestBody ReservationRequest reservationRequest,
                                       @PathVariable (value = "id") Long id){

        return reservationService.update(reservationRequest,id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete (@PathVariable (value = "id") Long id){

        reservationService.delete(id);
    }
}
