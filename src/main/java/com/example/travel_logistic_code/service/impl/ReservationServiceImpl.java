package com.example.travel_logistic_code.service.impl;

import com.example.travel_logistic_code.dto.request.ReservationRequest;
import com.example.travel_logistic_code.dto.response.ReservationResponse;
import com.example.travel_logistic_code.entity.Client;
import com.example.travel_logistic_code.entity.Driver;
import com.example.travel_logistic_code.entity.Reservation;
import com.example.travel_logistic_code.entity.Vehicle;
import com.example.travel_logistic_code.exception.TravelNotFoundException;
import com.example.travel_logistic_code.repository.ClientRepository;
import com.example.travel_logistic_code.repository.DriverRepository;
import com.example.travel_logistic_code.repository.ReservationRepository;
import com.example.travel_logistic_code.repository.VehicleRepository;
import com.example.travel_logistic_code.service.ReservationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static com.example.travel_logistic_code.exception.MessageConfirmation.TRAVEL_NOT_FOUND;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final VehicleRepository vehicleRepository;
    private final DriverRepository driverRepository;
    private final ClientRepository clientRepository;
    private final ReservationRepository reservationRepository;


    public ReservationServiceImpl(VehicleRepository vehicleRepository, DriverRepository driverRepository,
                                  ClientRepository clientRepository, ReservationRepository reservationRepository){
        this.vehicleRepository = vehicleRepository;
        this.driverRepository = driverRepository;
        this.clientRepository = clientRepository;
        this.reservationRepository = reservationRepository;
    }

    @Transactional
    @Override
    public ReservationResponse save (ReservationRequest reservationRequest) {


        Client existingClient = clientRepository.findById(reservationRequest.clientId())
                .orElseThrow(()-> new NoSuchElementException
                        ("Client not found with id:" + reservationRequest.clientId()));

        Driver existingDriver = driverRepository.findById(reservationRequest.driverId())
                .orElseThrow(()-> new NoSuchElementException
                        ("Vehicle not found with id:" + reservationRequest.driverId()));

        Vehicle existingVehicle = vehicleRepository.findById(reservationRequest.vehicleId())
                .orElseThrow(()-> new NoSuchElementException
                        ("Vehicle not found with id:" + reservationRequest.vehicleId()));



        //From Request to Entity
        Reservation newReservation = new Reservation();

        newReservation.setClient(existingClient);
        newReservation.setDriver(existingDriver);
        newReservation.setVehicle(existingVehicle);
        newReservation.setReservationDate(LocalDateTime.parse(reservationRequest.reservationDate()));
        newReservation.setStartDate(LocalDateTime.parse(reservationRequest.startDate()));
        newReservation.setEndDate(LocalDateTime.parse(reservationRequest.endDate()));
        newReservation.setCost(reservationRequest.cost());

        //Saving Entity
        Reservation savedReservation = reservationRepository.save(newReservation);

        //From Entity to ResponseDTO
        return new ReservationResponse
                (
                        savedReservation.getId(),
                        savedReservation.getReservationDate().toString(),
                        savedReservation.getStartDate().toString(),
                        savedReservation.getEndDate().toString(),
                        savedReservation.getDriver().getId(),
                        savedReservation.getDriver().getName(),
                        savedReservation.getDriver().getLastName(),
                        savedReservation.getVehicle().getId(),
                        savedReservation.getVehicle().getBrand(),
                        savedReservation.getVehicle().getModel(),
                        savedReservation.getCost(),
                        savedReservation.getStatus().name(),
                        savedReservation.getClient().getName(),
                        savedReservation.getClient().getLastName()


                );

    }

    @Override
    public List<ReservationResponse> getAll() {

        List<Reservation> reservationList = reservationRepository.findAll();
        List<ReservationResponse> responseList = new ArrayList<>();

        if(reservationList.isEmpty()){
            throw new NoSuchElementException("There are no trips registered in your system yet");
        }

        for(Reservation reservation : reservationList){

            ReservationResponse reservationResponse = new ReservationResponse
                    (
                            reservation.getId(),
                            reservation.getReservationDate().toString(),
                            reservation.getStartDate().toString(),
                            reservation.getEndDate().toString(),
                            reservation.getDriver().getId(),
                            reservation.getDriver().getName(),
                            reservation.getDriver().getLastName(),
                            reservation.getVehicle().getId(),
                            reservation.getVehicle().getBrand(),
                            reservation.getVehicle().getModel(),
                            reservation.getCost(),
                            reservation.getStatus().name(),
                            reservation.getClient().getName(),
                            reservation.getClient().getLastName()
                    );

            responseList.add(reservationResponse);
        }

        return responseList;
    }

    @Override
    public ReservationResponse getById(Long id) {

        Reservation existingReservation = reservationRepository.findById(id)
                .orElseThrow(()-> new TravelNotFoundException(TRAVEL_NOT_FOUND.getMessage()));

        return new ReservationResponse
                (
                        existingReservation.getId(),
                        existingReservation.getReservationDate().toString(),
                        existingReservation.getStartDate().toString(),
                        existingReservation.getEndDate().toString(),
                        existingReservation.getDriver().getId(),
                        existingReservation.getDriver().getName(),
                        existingReservation.getDriver().getLastName(),
                        existingReservation.getVehicle().getId(),
                        existingReservation.getVehicle().getBrand(),
                        existingReservation.getVehicle().getModel(),
                        existingReservation.getCost(),
                        existingReservation.getStatus().name(),
                        existingReservation.getClient().getName(),
                        existingReservation.getClient().getLastName()
                );

    }

    @Transactional
    @Override
    public ReservationResponse update(ReservationRequest reservationRequest, Long id) {

        reservationRepository.findById(id)
                .orElseThrow(()-> new TravelNotFoundException(TRAVEL_NOT_FOUND.getMessage() + id));


        Client existingClient = clientRepository.findById(reservationRequest.clientId())
                .orElseThrow(()-> new NoSuchElementException
                        ("Client not found with id:" + reservationRequest.clientId()));

        Driver existingDriver = driverRepository.findById(reservationRequest.driverId())
                .orElseThrow(()-> new NoSuchElementException
                        ("Vehicle not found with id:" + reservationRequest.driverId()));

        Vehicle existingVehicle = vehicleRepository.findById(reservationRequest.vehicleId())
                .orElseThrow(()-> new NoSuchElementException
                        ("Vehicle not found with id:" + reservationRequest.vehicleId()));

        //Modifying reservation
        Reservation modifyingReservation = new Reservation();

        modifyingReservation.setClient(existingClient);
        modifyingReservation.setDriver(existingDriver);
        modifyingReservation.setVehicle(existingVehicle);
        modifyingReservation.setReservationDate(LocalDateTime.parse(reservationRequest.reservationDate()));
        modifyingReservation.setStartDate(LocalDateTime.parse(reservationRequest.startDate()));
        modifyingReservation.setEndDate(LocalDateTime.parse(reservationRequest.endDate()));
        modifyingReservation.setCost(reservationRequest.cost());

        //Updating reservation
        Reservation updatedReservation = reservationRepository.save(modifyingReservation);

        return new ReservationResponse
                (
                        updatedReservation.getId(),
                        updatedReservation.getReservationDate().toString(),
                        updatedReservation.getStartDate().toString(),
                        updatedReservation.getEndDate().toString(),
                        updatedReservation.getDriver().getId(),
                        updatedReservation.getDriver().getName(),
                        updatedReservation.getDriver().getLastName(),
                        updatedReservation.getVehicle().getId(),
                        updatedReservation.getVehicle().getBrand(),
                        updatedReservation.getVehicle().getModel(),
                        updatedReservation.getCost(),
                        updatedReservation.getStatus().name(),
                        updatedReservation.getClient().getName(),
                        updatedReservation.getClient().getLastName()

                );

    }

    @Override
    public void delete(Long id) {

        reservationRepository.findById(id)
                .orElseThrow(()-> new TravelNotFoundException(TRAVEL_NOT_FOUND.getMessage()));

        reservationRepository.deleteById(id);

    }
}
