package com.example.travel_logistic_code.service;

import com.example.travel_logistic_code.dto.request.UserRequestDTO;
import com.example.travel_logistic_code.dto.response.UserResponseDTO;

import java.util.List;

public interface UserService {

    UserResponseDTO save (UserRequestDTO userRequestDTO);
    List<UserResponseDTO> getAll ();
    UserResponseDTO getById (Long id);
    UserResponseDTO update (UserRequestDTO userRequestDTO, Long id);
    void delete (Long id);

}
