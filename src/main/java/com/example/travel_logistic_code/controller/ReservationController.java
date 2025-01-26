package com.example.travel_logistic_code.controller;

import com.example.travel_logistic_code.dto.request.ReservationRequest;
import com.example.travel_logistic_code.dto.response.CancelReservationResponse;
import com.example.travel_logistic_code.dto.response.SaveReservationResponse;
import com.example.travel_logistic_code.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SaveReservationResponse save (@Valid @RequestBody ReservationRequest reservationRequest){

        return reservationService.save(reservationRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<SaveReservationResponse> getAll (){

        return reservationService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SaveReservationResponse getById (@PathVariable (value = "id") Long id){

        return reservationService.getById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SaveReservationResponse update (@Valid @RequestBody ReservationRequest reservationRequest,
                                           @PathVariable (value = "id") Long id){

        return reservationService.update(reservationRequest,id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CancelReservationResponse cancel (@PathVariable (value = "id") Long id ){

        return reservationService.cancel(id);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete (@PathVariable (value = "id") Long id){

        reservationService.delete(id);
    }
}
