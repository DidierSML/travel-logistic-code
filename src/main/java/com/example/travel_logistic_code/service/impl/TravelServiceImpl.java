package com.example.travel_logistic_code.service.impl;

import com.example.travel_logistic_code.dto.request.TravelRequestDTO;
import com.example.travel_logistic_code.dto.response.TravelResponseDTO;
import com.example.travel_logistic_code.exception.TravelNotFoundException;
import com.example.travel_logistic_code.model.*;
import com.example.travel_logistic_code.repository.DriverRepository;
import com.example.travel_logistic_code.repository.UserRepository;
import com.example.travel_logistic_code.repository.TravelRepository;
import com.example.travel_logistic_code.repository.VehicleRepository;
import com.example.travel_logistic_code.service.TravelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static com.example.travel_logistic_code.model.MessageConfirmation.TRAVEL_NOT_FOUND;

@Service
public class TravelServiceImpl implements TravelService {

    private final VehicleRepository vehicleRepository;
    private final DriverRepository driverRepository;
    private final UserRepository userRepository;
    private final TravelRepository travelRepository;


    public TravelServiceImpl(VehicleRepository vehicleRepository, DriverRepository driverRepository,
                             UserRepository userRepository, TravelRepository travelRepository){
        this.vehicleRepository = vehicleRepository;
        this.driverRepository = driverRepository;
        this.userRepository = userRepository;
        this.travelRepository = travelRepository;
    }

    @Transactional
    @Override
    public TravelResponseDTO save(TravelRequestDTO travelRequestDTO) {

        Vehicle existingVehicle = vehicleRepository.findById(travelRequestDTO.vehicleId())
                .orElseThrow(()-> new NoSuchElementException("Vehicle not found with id:" + travelRequestDTO.vehicleId()));

        Driver existingDriver = driverRepository.findById(travelRequestDTO.driverId())
                .orElseThrow(()-> new NoSuchElementException("Vehicle not found with id:" + travelRequestDTO.driverId()));

        User existingUser = userRepository.findById(travelRequestDTO.passengerId())
                .orElseThrow(()-> new NoSuchElementException("Vehicle not found with id:" + travelRequestDTO.passengerId()));

        //From RequestDTO to Entity
        Travel travel = new Travel();
        travel.setVehicle(existingVehicle);
        travel.setDriver(existingDriver);
        travel.setUser(existingUser);
        travel.setDayOfService(Day.valueOf(travelRequestDTO.dayOfService()));

        //Saving Entity
        Travel savedTravel = travelRepository.save(travel);

        //From Entity to ResponseDTO
        return new TravelResponseDTO
                (
                        savedTravel.getIdTravel(),
                        savedTravel.getVehicle().getBrand(),
                        savedTravel.getDriver().getName(),
                        savedTravel.getDriver().getLastName(),
                        savedTravel.getUser().getName(),
                        savedTravel.getUser().getLastName(),
                        savedTravel.getDayOfService().toString(),
                        MessageConfirmation.CONFIRMED_TRAVEL.getMessage()
                );

    }

    @Override
    public List<TravelResponseDTO> getAll() {

        List<Travel> travelList = travelRepository.findAll();
        List<TravelResponseDTO> responseList = new ArrayList<>();

        if(travelList.isEmpty()){
            throw new NoSuchElementException("There are no trips registered in your system yet");
        }

        for(Travel travel: travelList){

            TravelResponseDTO travelResponseDTO = new TravelResponseDTO
                    (
                            travel.getIdTravel(),
                            travel.getVehicle().getBrand(),
                            travel.getDriver().getName(),
                            travel.getDriver().getLastName(),
                            travel.getUser().getName(),
                            travel.getUser().getLastName(),
                            travel.getDayOfService().toString(),
                            MessageConfirmation.TRAVEL_FETCH_SUCCESS.getMessage()
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
                        existingTravel.getDriver().getLastName(),
                        existingTravel.getUser().getName(),
                        existingTravel.getUser().getLastName(),
                        existingTravel.getDayOfService().toString(),
                        MessageConfirmation.TRAVEL_FETCH_SUCCESS.getMessage()
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

        User existingUser = userRepository.findById(travelRequestDTO.passengerId())
                .orElseThrow(()-> new NoSuchElementException("Vehicle not found with id:" + travelRequestDTO.passengerId()));


        Travel modifyingTravel = new Travel();

        modifyingTravel.setVehicle(existingVehicle);
        modifyingTravel.setDriver(existingDriver);
        modifyingTravel.setUser(existingUser);
        modifyingTravel.setDayOfService(Day.valueOf(travelRequestDTO.dayOfService()));

        Travel updatedTravel = travelRepository.save(modifyingTravel);

        return new TravelResponseDTO
                (
                        updatedTravel.getIdTravel(),
                        updatedTravel.getVehicle().getBrand(),
                        updatedTravel.getDriver().getName(),
                        updatedTravel.getDriver().getLastName(),
                        updatedTravel.getUser().getName(),
                        updatedTravel.getUser().getLastName(),
                        updatedTravel.getDayOfService().toString(),
                        MessageConfirmation.TRAVEL_UPDATED.getMessage()

                );

    }

    @Override
    public void delete(Long id) {

        travelRepository.findById(id)
                .orElseThrow(()-> new TravelNotFoundException(TRAVEL_NOT_FOUND.getMessage()));

        travelRepository.deleteById(id);

    }
}
