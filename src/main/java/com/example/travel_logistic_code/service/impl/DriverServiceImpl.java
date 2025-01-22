package com.example.travel_logistic_code.service.impl;

import com.example.travel_logistic_code.dto.request.DriverRequest;
import com.example.travel_logistic_code.dto.response.DriverResponse;
import com.example.travel_logistic_code.dto.request.UserRequest;
import com.example.travel_logistic_code.entity.Driver;
import com.example.travel_logistic_code.repository.DriverRepository;
import com.example.travel_logistic_code.service.DriverService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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
    public DriverResponse save (DriverRequest driverRequest) {

        Driver newDriver = new Driver();
        UserRequest userRequest = driverRequest.userRequest();//Composición

        newDriver.setName(userRequest.name());
        newDriver.setLastName(userRequest.lastName());
        newDriver.setEmail(userRequest.email());
        newDriver.setPassword(userRequest.password());

        newDriver.setLicenseNumber(driverRequest.licenseNumber());
        newDriver.setLicenseExpiryDate(LocalDate.parse(driverRequest.licenseExpiryDate()));


        if(driverRepository.existsByLicenseNumber(newDriver.getLicenseNumber())){
            throw new NoSuchElementException
                    ("Driver with license:" + newDriver.getLicenseNumber() + " already exists in our System");
        }

        driverRepository.save(newDriver);

        return new DriverResponse
                (
                        newDriver.getId(),
                        newDriver.getName(),
                        newDriver.getLastName(),
                        newDriver.getEmail(),
                        newDriver.getLicenseNumber(),
                        newDriver.getLicenseExpiryDate().toString()

                );
    }

    @Override
    public List<DriverResponse> getAll() {

        List<Driver> existingDrivers = driverRepository.findAll();
        List<DriverResponse> responseList = new ArrayList<>();

        for(Driver driver: existingDrivers){

            DriverResponse driverResponse = new DriverResponse
                    (
                            driver.getId(),
                            driver.getName(),
                            driver.getLastName(),
                            driver.getEmail(),
                            driver.getLicenseNumber(),
                            driver.getLicenseExpiryDate().toString()
                    );

            responseList.add(driverResponse);
        }

        return responseList;
    }

    @Override
    public DriverResponse getById(Long id) {

        Driver existingDriver = driverRepository.findById(id).
                orElseThrow(()-> new NoSuchElementException("Driver with id:" + id + " does not exist in our System"));

        return new DriverResponse
                (
                        existingDriver.getId(),
                        existingDriver.getName(),
                        existingDriver.getLastName(),
                        existingDriver.getEmail(),
                        existingDriver.getLicenseNumber(),
                        existingDriver.getLicenseExpiryDate().toString()
                );
    }

    @Transactional
    @Override
    public DriverResponse update(DriverRequest driverRequest, Long id) {

        Driver existingDriver = driverRepository.findById(id).
                orElseThrow(()-> new NoSuchElementException("Driver with id:" + id + " does not exist in our System"));

        UserRequest userRequest = driverRequest.userRequest();

        existingDriver.setName(userRequest.name());
        existingDriver.setLastName(userRequest.lastName());
        existingDriver.setEmail(userRequest.email());
        existingDriver.setPassword(userRequest.password());

        existingDriver.setLicenseNumber(driverRequest.licenseNumber());
        existingDriver.setLicenseExpiryDate(LocalDate.parse(driverRequest.licenseExpiryDate()));

        driverRepository.save(existingDriver);

        return new DriverResponse
                (
                        existingDriver.getId(),
                        existingDriver.getName(),
                        existingDriver.getLastName(),
                        existingDriver.getEmail(),
                        existingDriver.getLicenseNumber(),
                        existingDriver.getLicenseExpiryDate().toString()
                );
    }

    @Override
    public void delete(Long id) {

        driverRepository.findById(id).
                orElseThrow(()-> new NoSuchElementException("Driver with id:" + id + " does not exist in our System"));

        driverRepository.deleteById(id);
    }
}
