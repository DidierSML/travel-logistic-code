package com.example.travel_logistic_code.service.impl;

import com.example.travel_logistic_code.dto.request.ClientRequest;
import com.example.travel_logistic_code.dto.response.ClientResponse;
import com.example.travel_logistic_code.dto.request.UserRequest;
import com.example.travel_logistic_code.entity.Client;
import com.example.travel_logistic_code.entity.enums.RoleType;
import com.example.travel_logistic_code.repository.ClientRepository;
import com.example.travel_logistic_code.service.ClientService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    @Transactional
    @Override
    public ClientResponse save(ClientRequest clientRequest) {

        Client newClient = new Client();
        UserRequest userRequest = clientRequest.userRequest();

        newClient.setName(userRequest.name());
        newClient.setLastName(userRequest.lastName());
        newClient.setEmail(userRequest.email());
        newClient.setPassword(userRequest.password());
        newClient.setRole(RoleType.CLIENT);

        newClient.setLoyaltyCardNumber(clientRequest.loyaltyCardNumber());

        clientRepository.save(newClient);

        return new ClientResponse
                (
                        newClient.getId(),
                        newClient.getName(),
                        newClient.getLastName(),
                        newClient.getEmail(),
                        newClient.getRole().name(),
                        newClient.getLoyaltyCardNumber()
                );


    }

    @Override
    public List<ClientResponse> getAll() {

        List<Client> existingCustomers = clientRepository.findAll();
        List<ClientResponse> responseList = new ArrayList<>();

        for(Client client : existingCustomers){

            ClientResponse clientResponse = new ClientResponse
                    (
                            client.getId(),
                            client.getName(),
                            client.getLastName(),
                            client.getEmail(),
                            client.getRole().name(),
                            client.getLoyaltyCardNumber()
                    );

            responseList.add(clientResponse);
        }

        return responseList;
    }

    @Override
    public ClientResponse getById(Long id) {

        Client existingClient = clientRepository.findById(id).
                orElseThrow(()-> new NoSuchElementException
                        ("Passenger with id:" + id + " does not exist in our System"));

        return new ClientResponse
                (
                        existingClient.getId(),
                        existingClient.getName(),
                        existingClient.getLastName(),
                        existingClient.getEmail(),
                        existingClient.getRole().name(),
                        existingClient.getLoyaltyCardNumber()
                );

    }

    @Transactional
    @Override
    public ClientResponse update(ClientRequest clientRequest, Long id) {

        Client existingClient = clientRepository.findById(id).
                orElseThrow(()-> new NoSuchElementException
                        ("Passenger with id:" + id + " does not exist in our System"));

        UserRequest userRequest = clientRequest.userRequest();

        existingClient.setName(userRequest.name());
        existingClient.setLastName(userRequest.lastName());
        existingClient.setEmail(userRequest.email());
        existingClient.setPassword(userRequest.password());

        existingClient.setLoyaltyCardNumber(clientRequest.loyaltyCardNumber());

        clientRepository.save(existingClient);

        return new ClientResponse
                (
                        existingClient.getId(),
                        existingClient.getName(),
                        existingClient.getLastName(),
                        existingClient.getEmail(),
                        existingClient.getRole().name(),
                        existingClient.getLoyaltyCardNumber()
                );
    }

    @Override
    public void delete(Long id) {

        clientRepository.findById(id).
                orElseThrow(()-> new NoSuchElementException
                        ("Passenger with id:" + id + " does not exist in our System"));

        clientRepository.deleteById(id);
    }
}
