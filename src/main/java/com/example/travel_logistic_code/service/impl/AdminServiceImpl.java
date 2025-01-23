package com.example.travel_logistic_code.service.impl;

import com.example.travel_logistic_code.dto.request.AdminRequest;
import com.example.travel_logistic_code.dto.request.UserRequest;
import com.example.travel_logistic_code.dto.response.AdminResponse;
import com.example.travel_logistic_code.dto.response.DriverResponse;
import com.example.travel_logistic_code.entity.Admin;
import com.example.travel_logistic_code.repository.AdminRepository;
import com.example.travel_logistic_code.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    @Override
    public AdminResponse createAdmin(AdminRequest adminRequest) {

        Admin newAdmin = new Admin();
        UserRequest userRequest = adminRequest.userRequest();

        newAdmin.setName(userRequest.name());
        newAdmin.setLastName(userRequest.lastName());
        newAdmin.setEmail(userRequest.email());
        newAdmin.setPassword(userRequest.password());

        newAdmin.setAdminCode(adminRequest.adminCode());

        if(adminRepository.existsByAdminCode(newAdmin.getAdminCode())){
            throw new NoSuchElementException
                    ("Admin with adminCode: " + newAdmin.getAdminCode() + "already exists in our System");
        }

        adminRepository.save(newAdmin);

        return new AdminResponse
                (
                        newAdmin.getId(),
                        newAdmin.getName(),
                        newAdmin.getLastName(),
                        newAdmin.getEmail(),
                        newAdmin.getAdminCode()
                );
    }

    @Override
    public List<AdminResponse> getAll() {

        List<Admin> existingAdministrators = adminRepository.findAll();
        List<AdminResponse> responseList = new ArrayList<>();

        for(Admin admin: existingAdministrators){

            AdminResponse adminResponse = new AdminResponse
                    (
                            admin.getId(),
                            admin.getName(),
                            admin.getLastName(),
                            admin.getEmail(),
                            admin.getAdminCode()
                    );

            responseList.add(adminResponse);
        }

        return responseList;
    }

    @Override
    public AdminResponse getById(Long id) {

        Admin existingAdministrators = adminRepository.findById(id).
                orElseThrow(()-> new NoSuchElementException("Driver with id:" + id + " does not exist in our System"));

        return new AdminResponse
                (
                        existingAdministrators.getId(),
                        existingAdministrators.getName(),
                        existingAdministrators.getLastName(),
                        existingAdministrators.getEmail(),
                        existingAdministrators.getAdminCode()

                );
    }

    @Override
    public AdminResponse update(AdminRequest adminRequest, Long id) {

        Admin existingAdministrators = adminRepository.findById(id).
                orElseThrow(()-> new NoSuchElementException("Driver with id:" + id + " does not exist in our System"));

        UserRequest userRequest = adminRequest.userRequest();

        existingAdministrators.setName(userRequest.name());
        existingAdministrators.setLastName(userRequest.lastName());
        existingAdministrators.setEmail(userRequest.email());
        existingAdministrators.setPassword(userRequest.password());

        existingAdministrators.setAdminCode(adminRequest.adminCode());

        adminRepository.save(existingAdministrators);

        return new AdminResponse
                (
                        existingAdministrators.getId(),
                        existingAdministrators.getName(),
                        existingAdministrators.getLastName(),
                        existingAdministrators.getEmail(),
                        existingAdministrators.getAdminCode()
                );
    }

    @Override
    public void delete(Long id) {

        adminRepository.findById(id).
                orElseThrow(()-> new NoSuchElementException("Driver with id:" + id + " does not exist in our System"));

        adminRepository.deleteById(id);

    }
}
