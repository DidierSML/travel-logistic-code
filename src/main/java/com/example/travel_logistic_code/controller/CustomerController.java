package com.example.travel_logistic_code.controller;

import com.example.travel_logistic_code.dto.request.CustomerRequestDTO;
import com.example.travel_logistic_code.dto.response.CustomerResponseDTO;
import com.example.travel_logistic_code.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponseDTO save (@Valid @RequestBody CustomerRequestDTO customerRequestDTO){

        return customerService.save(customerRequestDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerResponseDTO> getAll (){

        return customerService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerResponseDTO getById (@PathVariable (value = "id") Long id){

        return customerService.getById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerResponseDTO update (@Valid @RequestBody CustomerRequestDTO customerRequestDTO,
                                       @PathVariable (value = "id") Long id){

        return customerService.update(customerRequestDTO,id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete (@PathVariable (value = "id") Long id){

        customerService.delete(id);
    }
}
