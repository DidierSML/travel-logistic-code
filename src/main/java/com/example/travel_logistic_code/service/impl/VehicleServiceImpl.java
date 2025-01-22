package com.example.travel_logistic_code.service.impl;

import com.example.travel_logistic_code.dto.request.VehicleRequest;
import com.example.travel_logistic_code.dto.response.VehicleResponse;
import com.example.travel_logistic_code.entity.Vehicle;
import com.example.travel_logistic_code.repository.VehicleRepository;
import com.example.travel_logistic_code.service.VehicleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository){
        this.vehicleRepository = vehicleRepository;
    }

    @Transactional
    @Override
    public VehicleResponse save (VehicleRequest vehicleRequest) {

        Vehicle newVehicle = new Vehicle();

        newVehicle.setLicensePlate(vehicleRequest.licensePlate());
        newVehicle.setBrand(vehicleRequest.brand());
        newVehicle.setModel(vehicleRequest.model());
        newVehicle.setYear(vehicleRequest.year());
        newVehicle.setSeats(vehicleRequest.seats());
        newVehicle.setColor(vehicleRequest.color());

        if(vehicleRepository.existsByLicensePlate(newVehicle.getLicensePlate())){
            throw new NoSuchElementException
                    ("Vehicle with license:" + newVehicle.getLicensePlate() + " already exists in our System");
        }

        vehicleRepository.save(newVehicle);

        return new VehicleResponse
                (
                        newVehicle.getId(),
                        newVehicle.getLicensePlate(),
                        newVehicle.getBrand(),
                        newVehicle.getModel(),
                        newVehicle.getYear(),
                        newVehicle.getSeats(),
                        newVehicle.getColor()
                );

    }

    @Override
    public List<VehicleResponse> getAll() {

        List<Vehicle> existingVehicles = vehicleRepository.findAll();

        List<VehicleResponse> responseList = new ArrayList<>();

        for(Vehicle vehicle: existingVehicles){

            VehicleResponse vehicleResponse = new VehicleResponse
                    (
                            vehicle.getId(),
                            vehicle.getLicensePlate(),
                            vehicle.getBrand(),
                            vehicle.getModel(),
                            vehicle.getYear(),
                            vehicle.getSeats(),
                            vehicle.getColor()
                    );

            responseList.add(vehicleResponse);
        }

        return responseList;

    }

    @Override
    public VehicleResponse getById (Long id) {

        Vehicle existingVehicle = vehicleRepository.findById(id).
                orElseThrow(()-> new NoSuchElementException
                        ("Vehicle with id:" + id + " does not exist in our System"));

        return new VehicleResponse(

                existingVehicle.getId(),
                existingVehicle.getLicensePlate(),
                existingVehicle.getBrand(),
                existingVehicle.getModel(),
                existingVehicle.getYear(),
                existingVehicle.getSeats(),
                existingVehicle.getColor()
        );
    }

    @Transactional
    @Override
    public VehicleResponse update(VehicleRequest vehicleRequest, Long id) {

        Vehicle existingVehicle = vehicleRepository.findById(id).
                orElseThrow(()-> new NoSuchElementException
                        ("Vehicle with id:" + id + " does not exist in our System"));

        existingVehicle.setLicensePlate(vehicleRequest.licensePlate());
        existingVehicle.setBrand(vehicleRequest.brand());
        existingVehicle.setModel(vehicleRequest.model());
        existingVehicle.setYear(vehicleRequest.year());
        existingVehicle.setSeats(vehicleRequest.seats());
        existingVehicle.setColor(vehicleRequest.color());

        existingVehicle.setSeats(vehicleRequest.seats());

        vehicleRepository.save(existingVehicle);

        return new VehicleResponse(

                existingVehicle.getId(),
                existingVehicle.getLicensePlate(),
                existingVehicle.getBrand(),
                existingVehicle.getModel(),
                existingVehicle.getYear(),
                existingVehicle.getSeats(),
                existingVehicle.getColor()
        );

    }

    @Override
    public void delete(Long id) {

        vehicleRepository.findById(id).
                orElseThrow(()-> new NoSuchElementException
                        ("Vehicle with id:" + id + " does not exist in our System"));

        vehicleRepository.deleteById(id);

    }
}
