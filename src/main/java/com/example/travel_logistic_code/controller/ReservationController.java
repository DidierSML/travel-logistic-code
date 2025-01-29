package com.example.travel_logistic_code.controller;

import com.example.travel_logistic_code.dto.request.ReservationRequest;
import com.example.travel_logistic_code.dto.request.UpdateReservationRequestAdmin;
import com.example.travel_logistic_code.dto.request.UpdateReservationRequestClient;
import com.example.travel_logistic_code.dto.response.CancelReservationResponse;
import com.example.travel_logistic_code.dto.response.ReservationResponse;
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
    public ReservationResponse save (@Valid @RequestBody ReservationRequest reservationRequestBase){

        return reservationService.save(reservationRequestBase);
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

    @PutMapping("/updateByAmin/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReservationResponse updateByAdmin (@Valid @RequestBody UpdateReservationRequestAdmin reservationRequest,
                                              @PathVariable (value = "id") Long id){

        return reservationService.updateByAdmin(reservationRequest,id);
    }

    @PatchMapping("/updateByClient/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReservationResponse updateByClient (@Valid @RequestBody UpdateReservationRequestClient reservationRequest,
                                              @PathVariable (value = "id") Long id){

        return reservationService.updateByClient(reservationRequest,id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReservationResponse update (@Valid @RequestBody ReservationRequest reservationRequestBase,
                                       @PathVariable (value = "id") Long id){

        return reservationService.update(reservationRequestBase,id);
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
