package com.example.travel_logistic_code.controller;

import com.example.travel_logistic_code.dto.request.UserRequestDTO;
import com.example.travel_logistic_code.dto.response.UserResponseDTO;
import com.example.travel_logistic_code.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO save (@Valid @RequestBody UserRequestDTO userRequestDTO){

        return userService.save(userRequestDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponseDTO> getAll (){

        return userService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDTO getById (@PathVariable (value = "id") Long id){

        return userService.getById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDTO update (@Valid @RequestBody UserRequestDTO userRequestDTO,
                                   @PathVariable (value = "id") Long id){

        return userService.update(userRequestDTO,id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete (@PathVariable (value = "id") Long id){

        userService.delete(id);
    }
}
