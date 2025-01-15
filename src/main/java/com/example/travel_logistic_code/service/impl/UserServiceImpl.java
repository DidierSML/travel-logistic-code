package com.example.travel_logistic_code.service.impl;

import com.example.travel_logistic_code.dto.request.UserRequestDTO;
import com.example.travel_logistic_code.dto.response.UserResponseDTO;
import com.example.travel_logistic_code.model.User;
import com.example.travel_logistic_code.repository.UserRepository;
import com.example.travel_logistic_code.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public UserResponseDTO save(UserRequestDTO userRequestDTO) {

        User newUser = new User();

        newUser.setName(userRequestDTO.name());
        newUser.setLastName(userRequestDTO.lastName());

        userRepository.save(newUser);

        return new UserResponseDTO
                (
                        newUser.getId(),
                        newUser.getName(),
                        newUser.getLastName()
                );


    }

    @Override
    public List<UserResponseDTO> getAll() {

        List<User> existingUsers = userRepository.findAll();
        List<UserResponseDTO> responseList = new ArrayList<>();

        for(User user : existingUsers){

            UserResponseDTO userResponseDTO = new UserResponseDTO
                    (
                            user.getId(),
                            user.getName(),
                            user.getLastName()
                    );

            responseList.add(userResponseDTO);
        }

        return responseList;
    }

    @Override
    public UserResponseDTO getById(Long id) {

        User existingUser = userRepository.findById(id).
                orElseThrow(()-> new NoSuchElementException("Passenger with id:" + id + " does not exist in our System"));

        return new UserResponseDTO
                (
                        existingUser.getId(),
                        existingUser.getName(),
                        existingUser.getLastName()
                );

    }

    @Transactional
    @Override
    public UserResponseDTO update(UserRequestDTO userRequestDTO, Long id) {

        User existingUser = userRepository.findById(id).
                orElseThrow(()-> new NoSuchElementException("Passenger with id:" + id + " does not exist in our System"));

        existingUser.setName(userRequestDTO.name());
        existingUser.setLastName(userRequestDTO.lastName());

        userRepository.save(existingUser);

        return new UserResponseDTO
                (
                        existingUser.getId(),
                        existingUser.getName(),
                        existingUser.getLastName()
                );
    }

    @Override
    public void delete(Long id) {

        userRepository.findById(id).
                orElseThrow(()-> new NoSuchElementException("Passenger with id:" + id + " does not exist in our System"));

        userRepository.deleteById(id);
    }
}
