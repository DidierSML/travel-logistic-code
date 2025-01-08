package com.example.travel_logistic_code.service.impl;

import com.example.travel_logistic_code.dto.request.PassengerRequestDTO;
import com.example.travel_logistic_code.dto.response.PassengerResponseDTO;
import com.example.travel_logistic_code.model.Passenger;
import com.example.travel_logistic_code.repository.PassengerRepository;
import com.example.travel_logistic_code.service.PassengerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        return null;
    }

    @Override
    public PassengerResponseDTO getById(Long id) {
        return null;
    }

    @Transactional
    @Override
    public PassengerResponseDTO update(PassengerRequestDTO passengerRequestDTO, Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
