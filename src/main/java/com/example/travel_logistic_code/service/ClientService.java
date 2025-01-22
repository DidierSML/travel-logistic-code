package com.example.travel_logistic_code.service;

import com.example.travel_logistic_code.dto.request.ClientRequest;
import com.example.travel_logistic_code.dto.response.ClientResponse;

import java.util.List;

public interface ClientService {

    ClientResponse save (ClientRequest clientRequest);
    List<ClientResponse> getAll ();
    ClientResponse getById (Long id);
    ClientResponse update (ClientRequest clientRequest, Long id);
    void delete (Long id);

}
