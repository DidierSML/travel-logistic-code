package com.example.travel_logistic_code.service.impl;

import com.example.travel_logistic_code.dto.request.ReservationRequest;
import com.example.travel_logistic_code.dto.request.UpdateReservationRequestAdmin;
import com.example.travel_logistic_code.dto.response.CancelReservationResponse;
import com.example.travel_logistic_code.dto.response.ReservationResponse;
import com.example.travel_logistic_code.entity.Client;
import com.example.travel_logistic_code.entity.Driver;
import com.example.travel_logistic_code.entity.Reservation;
import com.example.travel_logistic_code.entity.Vehicle;
import com.example.travel_logistic_code.entity.enums.GeneralStatus;
import com.example.travel_logistic_code.entity.enums.ReservationStatus;
import com.example.travel_logistic_code.exception.ReservationNotFoundException;
import com.example.travel_logistic_code.repository.ClientRepository;
import com.example.travel_logistic_code.repository.DriverRepository;
import com.example.travel_logistic_code.repository.ReservationRepository;
import com.example.travel_logistic_code.repository.VehicleRepository;
import com.example.travel_logistic_code.service.ReservationService;
import com.sun.jdi.request.InvalidRequestStateException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static com.example.travel_logistic_code.exception.MessageConfirmation.RESERVATION_NOT_FOUND;

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

        if(!existingDriver.getStatus().equals(GeneralStatus.AVAILABLE)){
            throw new IllegalArgumentException("Driver is not available");
        }

        Vehicle existingVehicle = vehicleRepository.findById(reservationRequest.vehicleId())
                .orElseThrow(()-> new NoSuchElementException
                        ("Vehicle not found with id:" + reservationRequest.vehicleId()));

        if (!existingVehicle.getStatus().equals(GeneralStatus.AVAILABLE)) {
            throw new IllegalArgumentException("Vehicle is not available");
        }

        //From Request to Entity
        Reservation newReservation = new Reservation();

        newReservation.setClient(existingClient);
        newReservation.setDriver(existingDriver);
        newReservation.setVehicle(existingVehicle);
        newReservation.setReservationDate(LocalDateTime.parse(reservationRequest.reservationDate()));
        newReservation.setStartDate(LocalDateTime.parse(reservationRequest.startDate()));
        newReservation.setEndDate(LocalDateTime.parse(reservationRequest.endDate()));
        newReservation.setCost(reservationRequest.cost());
        newReservation.setStatus(ReservationStatus.CONFIRMED);

        existingDriver.setStatus(GeneralStatus.OCCUPIED);
        existingVehicle.setStatus(GeneralStatus.OCCUPIED);

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
                        savedReservation.getClient().getLastName(),
                        "Your reservations has been confirmed successfully"


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
                            reservation.getClient().getLastName(),
                            "Confirmed reservation"
                    );

            responseList.add(reservationResponse);
        }

        return responseList;
    }

    @Override
    public ReservationResponse getById(Long id) {

        Reservation existingReservation = reservationRepository.findById(id)
                .orElseThrow(()-> new ReservationNotFoundException(RESERVATION_NOT_FOUND.getMessage()));

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
                        existingReservation.getClient().getLastName(),
                        "Confirmed Reservation"

                );

    }

    @Transactional
    @Override
    public ReservationResponse updateByAdmin (UpdateReservationRequestAdmin reservationRequest, Long id) {

        Reservation existingReservation = reservationRepository.findById(id).
                orElseThrow(()-> new ReservationNotFoundException(RESERVATION_NOT_FOUND.getMessage() + id));

        //Status Validations and Assignments for driver,vehicle and Reservation
        statusValidations(reservationRequest, existingReservation);


        //Transforming Strings objects from request to LocalDateTime objects
        LocalDateTime convertedStartDate = LocalDateTime.parse(reservationRequest.newStartDate());
        LocalDateTime convertedEndDate = LocalDateTime.parse(reservationRequest.newEndDate());

        //Dates Validations
        if(convertedStartDate.isAfter(convertedEndDate)){
            throw new IllegalArgumentException("Start date cannot be after end date");
        }

        if(convertedEndDate.isBefore(LocalDateTime.now())){
            throw new IllegalArgumentException("Start date cannot be in the past");
        }

        //Assignments for new StartDate and EndDate
        existingReservation.setReservationDate(LocalDateTime.now());
        existingReservation.setStartDate(convertedStartDate);
        existingReservation.setEndDate(convertedEndDate);

        //Saving modifications
        reservationRepository.save(existingReservation);

        //String updatingDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));

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
                        existingReservation.getClient().getLastName(),
                        "Your reservation has been updated successfully"
                );
    }

    private void statusValidations (UpdateReservationRequestAdmin requestAdmin, Reservation existingReservation){

        //Reservation Status Validation
        if(requestAdmin.newStatus() == ReservationStatus.CANCELLED
                || existingReservation.getStatus() == ReservationStatus.COMPLETED ){
            throw new InvalidRequestStateException("Status CANCELLED or COMPLETED can not be modified");
        }


        //Driver Existence and Status Validation
        Driver driver = driverRepository.findById(requestAdmin.newDriverId()).
                orElseThrow(()-> new NoSuchElementException("Driver not found"));

        if(driver.getStatus() != GeneralStatus.AVAILABLE){
            throw new IllegalArgumentException("Sorry, Driver with id: " + requestAdmin.newDriverId() + "is NOT AVAILABLE");
        }
        //New Driver assignment Status
        driver.setStatus(GeneralStatus.OCCUPIED);


        //Vehicle existence and status validation
        Vehicle vehicle = vehicleRepository.findById(requestAdmin.newVehicleId()).
                orElseThrow(()-> new NoSuchElementException("Vehicle not found"));

        if(vehicle.getStatus() != GeneralStatus.AVAILABLE){
            throw new IllegalArgumentException("Sorry, Vehicle with id: " + requestAdmin.newDriverId() + "is NOT AVAILABLE");
        }
        //New Vehicle assignment Status
        vehicle.setStatus(GeneralStatus.OCCUPIED);

        //New Reservation assignment Status
        existingReservation.setStatus(ReservationStatus.CONFIRMED);

    }

    @Transactional
    @Override
    public ReservationResponse update(ReservationRequest reservationRequest, Long id) {

        reservationRepository.findById(id)
                .orElseThrow(()-> new ReservationNotFoundException(RESERVATION_NOT_FOUND.getMessage() + id));


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
                        updatedReservation.getClient().getLastName(),
                        "Your reservation has been updated successfully"

                );

    }

    @Override
    public CancelReservationResponse cancel (Long id) {

        Reservation existingReservation = reservationRepository.findById(id)
                .orElseThrow(()-> new ReservationNotFoundException(RESERVATION_NOT_FOUND.getMessage() + id));

        if (existingReservation.getStatus().equals(ReservationStatus.CANCELLED)){
            throw new IllegalArgumentException("Reservation is already cancelled");
        }

        existingReservation.setStatus(ReservationStatus.CANCELLED);

        if(existingReservation.getDriver() != null){
            existingReservation.getDriver().setStatus(GeneralStatus.AVAILABLE);
        }

        if(existingReservation.getVehicle() != null){
            existingReservation.getVehicle().setStatus(GeneralStatus.AVAILABLE);
        }

        return new CancelReservationResponse
                (
                        existingReservation.getId(),
                        existingReservation.getStatus().name(),
                        "Reservation has been successfully canceled",
                        LocalDateTime.now()
                );

    }

    @Override
    public void delete(Long id) {

        reservationRepository.findById(id)
                .orElseThrow(()-> new ReservationNotFoundException(RESERVATION_NOT_FOUND.getMessage()));

        reservationRepository.deleteById(id);

    }
}
