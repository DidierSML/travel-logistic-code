package com.example.travel_logistic_code.controller;

import com.example.travel_logistic_code.dto.request.DriverRequest;
import com.example.travel_logistic_code.dto.response.DriverResponse;
import com.example.travel_logistic_code.service.DriverService;
import jakarta.validation.Valid;
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
    public DriverResponse save (@Valid @RequestBody DriverRequest driverRequest){

        return driverService.save(driverRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<DriverResponse> getAll (){

        return driverService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DriverResponse getById (@PathVariable (value = "id") Long id){

        return driverService.getById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DriverResponse update (@Valid @RequestBody DriverRequest driverRequest,
                                  @PathVariable (value = "id") Long id){

        return driverService.update(driverRequest,id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete (@PathVariable (value = "id") Long id){

        driverService.delete(id);
    }
}
