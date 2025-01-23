package com.example.travel_logistic_code.controller;

import com.example.travel_logistic_code.dto.request.AdminRequest;
import com.example.travel_logistic_code.dto.response.AdminResponse;
import com.example.travel_logistic_code.service.AdminService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AdminResponse save (@Valid @RequestBody AdminRequest adminRequest){

        return adminService.createAdmin(adminRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AdminResponse> getAll (){

        return adminService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AdminResponse getById (@PathVariable (value = "id") Long id){

        return adminService.getById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AdminResponse update (@Valid @RequestBody AdminRequest adminRequest,
                                 @PathVariable (value = "id") Long id){

        return adminService.update(adminRequest,id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete (@PathVariable (value = "id") Long id){

        adminService.delete(id);
    }
}

