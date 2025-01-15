package com.example.travel_logistic_code.service.impl;

import com.example.travel_logistic_code.dto.request.CustomerRequestDTO;
import com.example.travel_logistic_code.dto.response.CustomerResponseDTO;
import com.example.travel_logistic_code.model.Customer;
import com.example.travel_logistic_code.repository.CustomerRepository;
import com.example.travel_logistic_code.service.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @Transactional
    @Override
    public CustomerResponseDTO save(CustomerRequestDTO customerRequestDTO) {

        Customer newCustomer = new Customer();

        newCustomer.setName(customerRequestDTO.name());
        newCustomer.setLastName(customerRequestDTO.lastName());

        customerRepository.save(newCustomer);

        return new CustomerResponseDTO
                (
                        newCustomer.getId(),
                        newCustomer.getName(),
                        newCustomer.getLastName()
                );


    }

    @Override
    public List<CustomerResponseDTO> getAll() {

        List<Customer> existingCustomers = customerRepository.findAll();
        List<CustomerResponseDTO> responseList = new ArrayList<>();

        for(Customer customer : existingCustomers){

            CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO
                    (
                            customer.getId(),
                            customer.getName(),
                            customer.getLastName()
                    );

            responseList.add(customerResponseDTO);
        }

        return responseList;
    }

    @Override
    public CustomerResponseDTO getById(Long id) {

        Customer existingCustomer = customerRepository.findById(id).
                orElseThrow(()-> new NoSuchElementException("Passenger with id:" + id + " does not exist in our System"));

        return new CustomerResponseDTO
                (
                        existingCustomer.getId(),
                        existingCustomer.getName(),
                        existingCustomer.getLastName()
                );

    }

    @Transactional
    @Override
    public CustomerResponseDTO update(CustomerRequestDTO customerRequestDTO, Long id) {

        Customer existingCustomer = customerRepository.findById(id).
                orElseThrow(()-> new NoSuchElementException("Passenger with id:" + id + " does not exist in our System"));

        existingCustomer.setName(customerRequestDTO.name());
        existingCustomer.setLastName(customerRequestDTO.lastName());

        customerRepository.save(existingCustomer);

        return new CustomerResponseDTO
                (
                        existingCustomer.getId(),
                        existingCustomer.getName(),
                        existingCustomer.getLastName()
                );
    }

    @Override
    public void delete(Long id) {

        customerRepository.findById(id).
                orElseThrow(()-> new NoSuchElementException("Passenger with id:" + id + " does not exist in our System"));

        customerRepository.deleteById(id);
    }
}
