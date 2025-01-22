package com.example.travel_logistic_code.controller;

import com.example.travel_logistic_code.dto.request.ClientRequest;
import com.example.travel_logistic_code.dto.response.ClientResponse;
import com.example.travel_logistic_code.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientResponse save (@Valid @RequestBody ClientRequest clientRequest){

        return clientService.save(clientRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ClientResponse> getAll (){

        return clientService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClientResponse getById (@PathVariable (value = "id") Long id){

        return clientService.getById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClientResponse update (@Valid @RequestBody ClientRequest clientRequest,
                                  @PathVariable (value = "id") Long id){

        return clientService.update(clientRequest,id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete (@PathVariable (value = "id") Long id){

        clientService.delete(id);
    }
}
