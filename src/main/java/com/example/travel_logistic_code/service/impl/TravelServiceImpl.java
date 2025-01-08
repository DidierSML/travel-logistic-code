package com.example.travel_logistic_code.service.impl;

import com.example.travel_logistic_code.dto.request.TravelRequestDTO;
import com.example.travel_logistic_code.dto.response.TravelResponseDTO;
import com.example.travel_logistic_code.exception.TravelNotFoundException;
import com.example.travel_logistic_code.model.*;
import com.example.travel_logistic_code.repository.DriverRepository;
import com.example.travel_logistic_code.repository.PassengerRepository;
import com.example.travel_logistic_code.repository.TravelRepository;
import com.example.travel_logistic_code.repository.VehicleRepository;
import com.example.travel_logistic_code.service.TravelService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static com.example.travel_logistic_code.model.MessageConfirmation.TRAVEL_NOT_FOUND;

@Service
@AllArgsConstructor
public class TravelServiceImpl implements TravelService {


    private final VehicleRepository vehicleRepository;
    private final DriverRepository driverRepository;
    private final PassengerRepository passengerRepository;
    private final TravelRepository travelRepository;

    @Transactional
    @Override
    public TravelResponseDTO save(TravelRequestDTO travelRequestDTO) {

        Vehicle existingVehicle = vehicleRepository.findById(travelRequestDTO.vehicleId())
                .orElseThrow(()-> new NoSuchElementException("Vehicle not found with id:" + travelRequestDTO.vehicleId()));

        Driver existingDriver = driverRepository.findById(travelRequestDTO.driverId())
                .orElseThrow(()-> new NoSuchElementException("Vehicle not found with id:" + travelRequestDTO.driverId()));

        Passenger existingPassenger = passengerRepository.findById(travelRequestDTO.passengerId())
                .orElseThrow(()-> new NoSuchElementException("Vehicle not found with id:" + travelRequestDTO.passengerId()));

        //From RequestDTO to Entity
        Travel travel = new Travel();
        travel.setVehicle(existingVehicle);
        travel.setDriver(existingDriver);
        travel.setPassenger(existingPassenger);
        travel.setDayOfService(Day.valueOf(travelRequestDTO.dayOfService()));

        //Saving Entity
        Travel savedTravel = travelRepository.save(travel);

        //From Entity to ResponseDTO
        return new TravelResponseDTO
                (
                        savedTravel.getIdTravel(),
                        savedTravel.getVehicle().getBrand(),
                        savedTravel.getDriver().getName(),
                        savedTravel.getPassenger().getName(),
                        savedTravel.getDayOfService().toString(),
                        MessageConfirmation.CONFIRMED_TRAVEL
                );

    }

    @Override
    public List<TravelResponseDTO> getAll() {

        List<Travel> travelList = travelRepository.findAll();
        List<TravelResponseDTO> responseList = new ArrayList<>();

        if(travelList.isEmpty()){
            throw new TravelNotFoundException(TRAVEL_NOT_FOUND.getMessage());
        }

        for(Travel travel: travelList){

            TravelResponseDTO travelResponseDTO = new TravelResponseDTO
                    (
                            travel.getIdTravel(),
                            travel.getVehicle().getBrand(),
                            travel.getDriver().getName(),
                            travel.getPassenger().getName(),
                            travel.getDayOfService().toString(),
                            MessageConfirmation.TRAVEL_FETCH_SUCCESS
                    );

            responseList.add(travelResponseDTO);
        }

        return responseList;
    }

    @Override
    public TravelResponseDTO getById(Long id) {

        Travel existingTravel = travelRepository.findById(id)
                .orElseThrow(()-> new TravelNotFoundException(TRAVEL_NOT_FOUND.getMessage()));

        return new TravelResponseDTO
                (
                        existingTravel.getIdTravel(),
                        existingTravel.getVehicle().getBrand(),
                        existingTravel.getDriver().getName(),
                        existingTravel.getPassenger().getName(),
                        existingTravel.getDayOfService().toString(),
                        MessageConfirmation.TRAVEL_FETCH_SUCCESS
                );

    }

    @Transactional
    @Override
    public TravelResponseDTO update(TravelRequestDTO travelRequestDTO, Long id) {

        travelRepository.findById(id)
                .orElseThrow(()-> new TravelNotFoundException(TRAVEL_NOT_FOUND.getMessage()));

        Vehicle existingVehicle = vehicleRepository.findById(travelRequestDTO.vehicleId())
                .orElseThrow(()-> new NoSuchElementException("Vehicle not found with id:" + travelRequestDTO.vehicleId()));

        Driver existingDriver = driverRepository.findById(travelRequestDTO.driverId())
                .orElseThrow(()-> new NoSuchElementException("Vehicle not found with id:" + travelRequestDTO.driverId()));

        Passenger existingPassenger = passengerRepository.findById(travelRequestDTO.passengerId())
                .orElseThrow(()-> new NoSuchElementException("Vehicle not found with id:" + travelRequestDTO.passengerId()));


        Travel modifyingTravel = new Travel();

        modifyingTravel.setVehicle(existingVehicle);
        modifyingTravel.setDriver(existingDriver);
        modifyingTravel.setPassenger(existingPassenger);
        modifyingTravel.setDayOfService(Day.valueOf(travelRequestDTO.dayOfService()));

        Travel updatedTravel = travelRepository.save(modifyingTravel);

        return new TravelResponseDTO
                (
                        updatedTravel.getIdTravel(),
                        updatedTravel.getVehicle().getBrand(),
                        updatedTravel.getDriver().getName(),
                        updatedTravel.getPassenger().getName(),
                        updatedTravel.getDayOfService().toString(),
                        MessageConfirmation.TRAVEL_UPDATED

                );

    }

    @Override
    public void delete(Long id) {

        travelRepository.findById(id)
                .orElseThrow(()-> new TravelNotFoundException(TRAVEL_NOT_FOUND.getMessage()));

        travelRepository.deleteById(id);

    }
}
