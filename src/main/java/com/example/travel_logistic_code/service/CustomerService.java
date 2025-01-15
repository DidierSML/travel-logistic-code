package com.example.travel_logistic_code.service;

import com.example.travel_logistic_code.dto.request.CustomerRequestDTO;
import com.example.travel_logistic_code.dto.response.CustomerResponseDTO;

import java.util.List;

public interface CustomerService {

    CustomerResponseDTO save (CustomerRequestDTO customerRequestDTO);
    List<CustomerResponseDTO> getAll ();
    CustomerResponseDTO getById (Long id);
    CustomerResponseDTO update (CustomerRequestDTO customerRequestDTO, Long id);
    void delete (Long id);

}
