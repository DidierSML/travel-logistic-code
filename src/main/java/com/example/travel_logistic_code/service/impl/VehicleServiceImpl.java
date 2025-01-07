package com.example.travel_logistic_code.service.impl;

import com.example.travel_logistic_code.dto.request.VehicleRequestDTO;
import com.example.travel_logistic_code.dto.response.VehicleResponseDTO;
import com.example.travel_logistic_code.model.Vehicle;
import com.example.travel_logistic_code.repository.VehicleRepository;
import com.example.travel_logistic_code.service.VehicleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    @Transactional
    @Override
    public VehicleResponseDTO save (VehicleRequestDTO vehicleRequestDTO) {

        Vehicle newVehicle = new Vehicle();

        newVehicle.setId(vehicleRequestDTO.vehicleId());
        newVehicle.setBrand(vehicleRequestDTO.brand());
        newVehicle.setColor(vehicleRequestDTO.color());
        newVehicle.setLicensePlate(vehicleRequestDTO.licensePlate());
        newVehicle.setSeats(vehicleRequestDTO.seats());

        if(vehicleRepository.existsById(newVehicle.getId())){
            throw new NoSuchElementException("Vehicle with id:" + newVehicle.getId() + " already exists in our System");
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
                    vehicle.getSeats(),
                    vehicle.getLicensePlate()

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
