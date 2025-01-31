package com.example.travel_logistic_code.service.impl;

import com.example.travel_logistic_code.utils.ConversionToLocalDate;
import com.example.travel_logistic_code.dto.request.ReservationRequestSave;
import com.example.travel_logistic_code.dto.request.UpdateReservationRequestAdmin;
import com.example.travel_logistic_code.dto.request.ReservationRequestDates;
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

import java.time.LocalDate;
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
    public ReservationResponse save (ReservationRequestSave reservationRequestSave) {


        Client existingClient = clientRepository.findById(reservationRequestSave.clientId())
                .orElseThrow(()-> new NoSuchElementException
                        ("Client not found with id:" + reservationRequestSave.clientId()));

        Driver existingDriver = driverRepository.findById(reservationRequestSave.driverId())
                .orElseThrow(()-> new NoSuchElementException
                        ("Vehicle not found with id:" + reservationRequestSave.driverId()));

        if(!existingDriver.getStatus().equals(GeneralStatus.AVAILABLE)){
            throw new IllegalArgumentException("Driver is not available");
        }

        Vehicle existingVehicle = vehicleRepository.findById(reservationRequestSave.vehicleId())
                .orElseThrow(()-> new NoSuchElementException
                        ("Vehicle not found with id:" + reservationRequestSave.vehicleId()));

        if (!existingVehicle.getStatus().equals(GeneralStatus.AVAILABLE)) {
            throw new IllegalArgumentException("Vehicle is not available");
        }

        //From Request to Entity
        Reservation newReservation = new Reservation();

        newReservation.setClient(existingClient);
        newReservation.setDriver(existingDriver);
        newReservation.setVehicle(existingVehicle);

        //Transforming Strings objects from request to LocalDateTime objects & validations
        ConversionToLocalDate validatedDates = datesValidations(reservationRequestSave.reservationRequestDates());

        //Assignment of Reservation Date
        newReservation.setReservationDate(LocalDate.parse(reservationRequestSave.reservationDate()));

        //Assignments for new StartDate and EndDate
        newReservation.setStartDate(validatedDates.startDate());
        newReservation.setStartDate(validatedDates.endDate());

        newReservation.setCost(reservationRequestSave.cost());
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


    private ConversionToLocalDate datesValidations (ReservationRequestDates reservationRequestDates){

        //Transforming Strings objects from request to LocalDateTime objects
        LocalDate convertedStartDate = LocalDate.parse(reservationRequestDates.startDate());
        LocalDate convertedEndDate = LocalDate.parse(reservationRequestDates.endDate());

        //Dates Validations
        if(convertedStartDate.isAfter(convertedEndDate)){
            throw new IllegalArgumentException("Start date cannot be after end date");
        }

        if(convertedEndDate.isBefore(LocalDate.now())){
            throw new IllegalArgumentException("Start date cannot be in the past");
        }

        return new ConversionToLocalDate (convertedStartDate,convertedEndDate);
    }


    @Override
    public List<ReservationResponse> getAll() {

        List<Reservation> reservationList = reservationRepository.findAll();
        List<ReservationResponse> responseList = new ArrayList<>();

        if(reservationList.isEmpty()){
            throw new NoSuchElementException("There are no reservations registered in your system yet");
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

        //Reservation Status Validation
        if(existingReservation.getStatus() != ReservationStatus.CONFIRMED){
            throw new InvalidRequestStateException("Status CANCELLED or COMPLETED can not be modified");
        }

        //Status Validations and Assignments for Driver,Vehicle and Reservation
        statusValidations(existingReservation);

        //Transforming Strings objects from request to LocalDateTime objects & validations
        ConversionToLocalDate validatedDates = datesValidations(reservationRequest.reservationRequestDates());

        //Updating Date
        existingReservation.setReservationDate(LocalDate.now());

        //Assignments for new StartDate and EndDate
        existingReservation.setStartDate(validatedDates.startDate());
        existingReservation.setStartDate(validatedDates.endDate());


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


    @Transactional
    @Override
    public ReservationResponse updateByClient(ReservationRequestDates reservationRequest, Long id) {

        Reservation existingReservation = reservationRepository.findById(id).
                orElseThrow(()-> new ReservationNotFoundException(RESERVATION_NOT_FOUND.getMessage() + id));

        //Reservation Status Validation
        if(existingReservation.getStatus() != ReservationStatus.CONFIRMED){
            throw new InvalidRequestStateException("Status CANCELLED or COMPLETED can not be modified");
        }

        //Status Validations and Assignments for driver,vehicle and Reservation
        statusValidations(existingReservation);

        //Transforming Strings objects from request to LocalDateTime objects & validations
        ConversionToLocalDate validatedDates = datesValidations(reservationRequest);

        //Updating Reservation Date
        existingReservation.setReservationDate(LocalDate.now());

        //Assignments for new StartDate and EndDate
        existingReservation.setStartDate(validatedDates.startDate());
        existingReservation.setStartDate(validatedDates.endDate());


        //Saving modifications
        reservationRepository.save(existingReservation);

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


    private void statusValidations (Reservation existingReservation){

        //Driver Existence and Status Validation
        Driver driver = driverRepository.findById(existingReservation.getDriver().getId()).
                orElseThrow(()-> new NoSuchElementException("Driver not found"));

        if(driver.getStatus() != GeneralStatus.AVAILABLE){
            throw new IllegalArgumentException("Sorry, Driver with id: " + existingReservation.getDriver().getId() + "is NOT AVAILABLE");
        }
        //New Driver assignment Status
        driver.setStatus(GeneralStatus.OCCUPIED);


        //Vehicle existence and status validation
        Vehicle vehicle = vehicleRepository.findById(existingReservation.getVehicle().getId()).
                orElseThrow(()-> new NoSuchElementException("Vehicle not found"));

        if(vehicle.getStatus() != GeneralStatus.AVAILABLE){
            throw new IllegalArgumentException("Sorry, Vehicle with id: " + existingReservation.getVehicle().getId() + "is NOT AVAILABLE");
        }
        //New Vehicle assignment Status
        vehicle.setStatus(GeneralStatus.OCCUPIED);

        //New Reservation assignment Status
        existingReservation.setStatus(ReservationStatus.CONFIRMED);

    }

//    private void completedStatus (){
//
//    }


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
