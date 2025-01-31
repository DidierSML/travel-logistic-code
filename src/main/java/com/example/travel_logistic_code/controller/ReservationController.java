package com.example.travel_logistic_code.controller;

import com.example.travel_logistic_code.dto.request.ReservationRequestSave;
import com.example.travel_logistic_code.dto.request.UpdateReservationRequestAdmin;
import com.example.travel_logistic_code.dto.request.ReservationRequestDates;
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
    public ReservationResponse save (@Valid @RequestBody ReservationRequestSave reservationRequestSaveBase){

        return reservationService.save(reservationRequestSaveBase);
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
    public ReservationResponse updateByClient (@Valid @RequestBody ReservationRequestDates reservationRequest,
                                              @PathVariable (value = "id") Long id){

        return reservationService.updateByClient(reservationRequest,id);
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
