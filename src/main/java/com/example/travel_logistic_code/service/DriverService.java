package com.example.travel_logistic_code.service;

import com.example.travel_logistic_code.dto.request.DriverRequest;
import com.example.travel_logistic_code.dto.response.DriverResponse;


import java.util.List;

public interface DriverService  {

    DriverResponse save (DriverRequest driverRequest);

    List<DriverResponse> getAll ();
    DriverResponse getById (Long id);
    DriverResponse update (DriverRequest driverRequest, Long id);
    void delete (Long id);
}
