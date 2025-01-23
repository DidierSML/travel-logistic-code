package com.example.travel_logistic_code.service;

import com.example.travel_logistic_code.dto.request.AdminRequest;
import com.example.travel_logistic_code.dto.response.AdminResponse;

import java.util.List;

public interface AdminService {

    AdminResponse createAdmin (AdminRequest adminRequest);
    List <AdminResponse> getAll ();
    AdminResponse getById (Long id);
    AdminResponse update (AdminRequest adminRequest, Long id);
    void delete (Long id);
}
