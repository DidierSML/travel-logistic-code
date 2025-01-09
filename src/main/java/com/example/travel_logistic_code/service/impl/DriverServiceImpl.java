package com.example.travel_logistic_code.service.impl;

import com.example.travel_logistic_code.dto.request.DriverRequestDTO;
import com.example.travel_logistic_code.dto.response.DriverResponseDTO;
import com.example.travel_logistic_code.model.Driver;
import com.example.travel_logistic_code.repository.DriverRepository;
import com.example.travel_logistic_code.service.DriverService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;

    public DriverServiceImpl(DriverRepository driverRepository){
        this.driverRepository = driverRepository;
    }

    @Transactional
    @Override
    public DriverResponseDTO save(DriverRequestDTO driverRequestDTO) {

        Driver newDriver = new Driver();

        newDriver.setName(driverRequestDTO.name());
        newDriver.setLastName(driverRequestDTO.lastName());
        newDriver.setDriverLicense(driverRequestDTO.driverLicense());

        if(driverRepository.existsByDriverLicense(newDriver.getDriverLicense())){
            throw new NoSuchElementException("Driver with license:" + newDriver.getDriverLicense() + " already exists in our System");
        }

        driverRepository.save(newDriver);

        return new DriverResponseDTO
                (
                    newDriver.getId(),
                    newDriver.getName(),
                    newDriver.getLastName(),
                    newDriver.getDriverLicense()

                );
    }

    @Override
    public List<DriverResponseDTO> getAll() {

        List<Driver> existingDrivers = driverRepository.findAll();
        List<DriverResponseDTO> responseList = new ArrayList<>();

        for(Driver driver: existingDrivers){

            DriverResponseDTO driverResponseDTO = new DriverResponseDTO
                    (
                            driver.getId(),
                            driver.getName(),
                            driver.getLastName(),
                            driver.getLastName()
                    );

            responseList.add(driverResponseDTO);
        }

        return responseList;
    }

    @Override
    public DriverResponseDTO getById(Long id) {

        Driver existingDriver = driverRepository.findById(id).
                orElseThrow(()-> new NoSuchElementException("Driver with id:" + id + " does not exist in our System"));

        return new DriverResponseDTO
                (
                        existingDriver.getId(),
                        existingDriver.getName(),
                        existingDriver.getLastName(),
                        existingDriver.getDriverLicense()
                );
    }

    @Transactional
    @Override
    public DriverResponseDTO update(DriverRequestDTO driverRequestDTO, Long id) {

        Driver existingDriver = driverRepository.findById(id).
                orElseThrow(()-> new NoSuchElementException("Driver with id:" + id + " does not exist in our System"));

        existingDriver.setName(driverRequestDTO.name());
        existingDriver.setLastName(driverRequestDTO.lastName());
        existingDriver.setDriverLicense(driverRequestDTO.driverLicense());

        driverRepository.save(existingDriver);

        return new DriverResponseDTO
                (
                        existingDriver.getId(),
                        existingDriver.getName(),
                        existingDriver.getLastName(),
                        existingDriver.getDriverLicense()
                );
    }

    @Override
    public void delete(Long id) {

        driverRepository.findById(id).
                orElseThrow(()-> new NoSuchElementException("Driver with id:" + id + " does not exist in our System"));

        driverRepository.deleteById(id);
    }
}
