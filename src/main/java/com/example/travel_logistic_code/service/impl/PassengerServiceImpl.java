package com.example.travel_logistic_code.service.impl;

import com.example.travel_logistic_code.dto.request.PassengerRequestDTO;
import com.example.travel_logistic_code.dto.response.PassengerResponseDTO;
import com.example.travel_logistic_code.model.Passenger;
import com.example.travel_logistic_code.repository.PassengerRepository;
import com.example.travel_logistic_code.service.PassengerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class PassengerServiceImpl implements PassengerService {

    private final PassengerRepository passengerRepository;

    @Transactional
    @Override
    public PassengerResponseDTO save(PassengerRequestDTO passengerRequestDTO) {

        Passenger newPassenger = new Passenger();

        newPassenger.setId(passengerRequestDTO.passengerId());
        newPassenger.setName(passengerRequestDTO.name());
        newPassenger.setLastName(passengerRequestDTO.lastName());

        if(passengerRepository.existsById(passengerRequestDTO.passengerId())){
            throw new NoSuchElementException("Vehicle with id:" + newPassenger.getId() + " already exists in our System");
        }

        passengerRepository.save(newPassenger);

        return new PassengerResponseDTO
                (
                        newPassenger.getId(),
                        newPassenger.getName(),
                        newPassenger.getLastName()
                );


    }

    @Override
    public List<PassengerResponseDTO> getAll() {

        List<Passenger> existingPassengers = passengerRepository.findAll();
        List<PassengerResponseDTO> responseList = new ArrayList<>();

        for(Passenger passenger: existingPassengers){

            PassengerResponseDTO passengerResponseDTO = new PassengerResponseDTO
                    (
                            passenger.getId(),
                            passenger.getName(),
                            passenger.getLastName()
                    );

            responseList.add(passengerResponseDTO);
        }

        return responseList;
    }

    @Override
    public PassengerResponseDTO getById(Long id) {

        Passenger existingPassenger = passengerRepository.findById(id).
                orElseThrow(()-> new NoSuchElementException("Passenger with id:" + id + " does not exist in our System"));

        return new PassengerResponseDTO
                (
                        existingPassenger.getId(),
                        existingPassenger.getName(),
                        existingPassenger.getLastName()
                );

    }

    @Transactional
    @Override
    public PassengerResponseDTO update(PassengerRequestDTO passengerRequestDTO, Long id) {

        Passenger existingPassenger = passengerRepository.findById(id).
                orElseThrow(()-> new NoSuchElementException("Passenger with id:" + id + " does not exist in our System"));

        existingPassenger.setName(passengerRequestDTO.name());
        existingPassenger.setLastName(passengerRequestDTO.lastName());

        passengerRepository.save(existingPassenger);

        return new PassengerResponseDTO
                (
                        existingPassenger.getId(),
                        existingPassenger.getName(),
                        existingPassenger.getLastName()
                );
    }

    @Override
    public void delete(Long id) {

        passengerRepository.findById(id).
                orElseThrow(()-> new NoSuchElementException("Passenger with id:" + id + " does not exist in our System"));

        passengerRepository.deleteById(id);
    }
}
