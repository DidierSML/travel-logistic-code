package com.example.travel_logistic_code.service.impl;

import com.example.travel_logistic_code.dto.request.VehicleRequestDTO;
import com.example.travel_logistic_code.dto.response.VehicleResponseDTO;
import com.example.travel_logistic_code.model.Vehicle;
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
    public VehicleResponseDTO save (VehicleRequestDTO vehicleRequestDTO) {

        Vehicle newVehicle = new Vehicle();

        newVehicle.setBrand(vehicleRequestDTO.brand());
        newVehicle.setColor(vehicleRequestDTO.color());
        newVehicle.setLicensePlate(vehicleRequestDTO.licensePlate());
        newVehicle.setSeats(vehicleRequestDTO.seats());

        if(vehicleRepository.existsByLicensePlate(newVehicle.getLicensePlate())){
            throw new NoSuchElementException("Vehicle with license:" + newVehicle.getLicensePlate() + " already exists in our System");
        }

        vehicleRepository.save(newVehicle);

        return new VehicleResponseDTO
                (
                        newVehicle.getId(),
                        newVehicle.getBrand(),
                        newVehicle.getColor(),
                        newVehicle.getLicensePlate(),
                        newVehicle.getSeats()
                );

    }

    @Override
    public List<VehicleResponseDTO> getAll() {

        List<Vehicle> existingVehicles = vehicleRepository.findAll();

        List<VehicleResponseDTO> responseList = new ArrayList<>();

        for(Vehicle vehicle: existingVehicles){

            VehicleResponseDTO vehicleResponseDTO = new VehicleResponseDTO
                    (
                            vehicle.getId(),
                            vehicle.getBrand(),
                            vehicle.getColor(),
                            vehicle.getLicensePlate(),
                            vehicle.getSeats()
                    );

            responseList.add(vehicleResponseDTO);
        }

        return responseList;

    }

    @Override
    public VehicleResponseDTO getById (Long id) {

        Vehicle existingVehicle = vehicleRepository.findById(id).
                orElseThrow(()-> new NoSuchElementException("Vehicle with id:" + id + " does not exist in our System"));

        return new VehicleResponseDTO(
                existingVehicle.getId(),
                existingVehicle.getBrand(),
                existingVehicle.getColor(),
                existingVehicle.getLicensePlate(),
                existingVehicle.getSeats()
        );
    }

    @Transactional
    @Override
    public VehicleResponseDTO update(VehicleRequestDTO vehicleRequestDTO, Long id) {

        Vehicle existingVehicle = vehicleRepository.findById(id).
                orElseThrow(()-> new NoSuchElementException("Vehicle with id:" + id + " does not exist in our System"));

        existingVehicle.setBrand(vehicleRequestDTO.brand());
        existingVehicle.setColor(vehicleRequestDTO.color());
        existingVehicle.setLicensePlate(vehicleRequestDTO.licensePlate());
        existingVehicle.setSeats(vehicleRequestDTO.seats());

        vehicleRepository.save(existingVehicle);

        return new VehicleResponseDTO(
                existingVehicle.getId(),
                existingVehicle.getBrand(),
                existingVehicle.getColor(),
                existingVehicle.getLicensePlate(),
                existingVehicle.getSeats()
        );

    }

    @Override
    public void delete(Long id) {

        vehicleRepository.findById(id).
                orElseThrow(()-> new NoSuchElementException("Vehicle with id:" + id + " does not exist in our System"));

        vehicleRepository.deleteById(id);

    }
}
